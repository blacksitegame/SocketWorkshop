import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class  TCPServer{
    public static void main(String[] args) throws Exception {

        ServerSocket welcomSocket = new ServerSocket(6789);
        System.out.println("Serveren venter på klient");
            Socket connectionSocket = welcomSocket.accept();
            if (connectionSocket.isConnected()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Ønsker du at chatte? (y/n)");
                String answer = scanner.next();
                if (answer.equals("y")) {
                    DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
                    dataOutputStream.writeBytes("Server Authorisation accepted");
                    System.out.println("Klient forbundet til Server");
                    TCPReceiverThread receiverThread = new TCPReceiverThread(connectionSocket);
                    TCPSendThread sendThread = new TCPSendThread(connectionSocket);
                    receiverThread.start();
                    sendThread.start();
                }
            }
    }
}


