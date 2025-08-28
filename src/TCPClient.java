import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private final static String DNSSERVER = "10.10.130.132";
    public static void main(String argv[]) throws Exception {
        Socket clientSocket = connectToClient("Jonas");
        TCPReceiverThread receiverThread = new TCPReceiverThread(clientSocket);
        TCPSendThread sendThread = new TCPSendThread(clientSocket);
        System.out.println("Venter på Server authorisation: ");
            receiverThread.start();
            sendThread.start();

    }
    public static Socket connectToClient(String CallID) throws IOException {
        Socket DNSSocket = new Socket(DNSSERVER, 7777);
        Socket clientSocket = null;
        if (!DNSSocket.isConnected()) {
            System.out.println("DNS Server not avaliable");
            return clientSocket;
        }
        PrintWriter out = new PrintWriter(DNSSocket.getOutputStream(), true); // auto-flush on println
        out.println(CallID);

        BufferedReader inFromDNS = new BufferedReader(new InputStreamReader(DNSSocket.getInputStream()));

        String ip = inFromDNS.readLine();
        DNSSocket.close();
        String[] DNSSplitSentence  = ip.split(" ");

        clientSocket = new Socket(DNSSplitSentence[0], Integer.parseInt((DNSSplitSentence[1])));
        return clientSocket;
    }
}
