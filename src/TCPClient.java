import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    public static void main(String argv[]) throws Exception {
        Socket clientSocket = new Socket("localhost", 6789);

        TCPReceiverThread receiverThread = new TCPReceiverThread(clientSocket);
        TCPSendThread sendThread = new TCPSendThread(clientSocket);
        System.out.println("Venter på Server authorisation: ");
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        if (inFromClient.readLine() != "Du har nu forbindelse"){
            System.out.println("Server authorisation received");
            receiverThread.start();
            sendThread.start();
        }
//        clientSocket.close();
    }
}
