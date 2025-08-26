import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) throws Exception {

    ServerSocket welcomSocket = new ServerSocket(6789);
    System.out.println("Serveren venter på klient");
    Socket connectionSocket = welcomSocket.accept();
        if (connectionSocket.isConnected()){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ønsker du at chatte? (y/n)");
            String answer = scanner.next();
            if (answer.equals("y")){

                    System.out.println("Klient forbundet til Server");
                    receive(connectionSocket);
                    send(connectionSocket);
            }
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



