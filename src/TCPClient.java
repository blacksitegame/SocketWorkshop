import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;


public class TCPClient {
    public static void main(String argv[]) throws Exception {
        while (true) {
            Socket clientSocket = new Socket("10.10.131.157", 6789);

            System.out.println(receive(clientSocket));
            send(clientSocket);
            clientSocket.close();
        }
    }

    public static String receive(Socket connectionSocket) throws IOException {
        String clientSentence;

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        clientSentence = inFromClient.readLine();
        return clientSentence;
    }

    public static void send(Socket connectionSocket) throws IOException {
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        outToClient.writeBytes(scanner.next());
    }
}

