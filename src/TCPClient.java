import java.net.Socket;

public class TCPClient {
    public static void main(String argv[]) throws Exception {
        Socket clientSocket = new Socket("localhost", 6789);

        TCPReceiverThread receiverThread = new TCPReceiverThread(clientSocket);
        TCPSendThread sendThread = new TCPSendThread(clientSocket);
        System.out.println("Venter på Server authorisation: ");
            receiverThread.start();
            sendThread.start();

//        clientSocket.close();
    }
}
