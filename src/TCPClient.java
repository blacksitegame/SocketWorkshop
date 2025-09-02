import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    private final static String DNSSERVER = "10.10.130.100";
    public static void main(String argv[]) throws Exception {
        //Socket clientSocket = connectToClient("Jonas");
        Socket clientSocket = connectToClientUDP("Rasmus");
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
    public static Socket connectToClientUDP(String CallID) throws IOException {
        byte[] receiveBuffer = new byte[1024];
        DatagramSocket DNSSocket = new DatagramSocket(6500);
        Socket clientSocket = null;

        byte[] CallIdByte = CallID.getBytes();
        InetAddress ip = InetAddress.getByName(DNSSERVER);
        DatagramPacket sendPacket = new DatagramPacket(CallIdByte,CallIdByte.length,ip,7778);
        DNSSocket.send(sendPacket);
        DNSSocket.send(sendPacket);

        DatagramPacket requestPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        DNSSocket.receive(requestPacket);

        String response = new String(requestPacket.getData(), 0, requestPacket.getLength()).trim();
        System.out.println("Received request from: " + requestPacket.getAddress() );
        String[] DNSSplitSentence  = response.split(" ");


        clientSocket = new Socket(DNSSplitSentence[0], Integer.parseInt((DNSSplitSentence[1])));
        return clientSocket;
    }

}
