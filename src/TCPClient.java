import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public static void main(String argv[]) throws Exception {
        Socket clientSocket = new Socket("10.10.131.157", 6789);

        TCPReceiverThread receiverThread = new TCPReceiverThread(clientSocket);
        TCPSendThread sendThread = new TCPSendThread(clientSocket);
        receiverThread.start();
        sendThread.start();
//        clientSocket.close();
}


