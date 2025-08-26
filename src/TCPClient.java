import java.net.Socket;

public class TCPClient {
    public static void main(String argv[]) throws Exception {
        Socket clientSocket = new Socket("10.10.131.157", 6789);

        TCPReceiverThread receiverThread = new TCPReceiverThread(clientSocket);
        TCPSendThread sendThread = new TCPSendThread(clientSocket);
        receiverThread.start();
        sendThread.start();
//        clientSocket.close();
    }
}
