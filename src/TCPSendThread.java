import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class TCPSendThread extends Thread{
    private Socket socket;
    private String message;

    public TCPSendThread(Socket socket) {
        this.socket = socket;
    }

    public void send() throws IOException {
        DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        message = scanner.nextLine();
        outToClient.writeBytes(message);
        System.out.println(message);
    }

    public void run(){
        try {
            send();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
