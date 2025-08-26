import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class TCPSendThread extends Thread{
    private Socket socket;
    private DataOutputStream outToClient;
    private Scanner scanner;

    public TCPSendThread(Socket socket) throws IOException {
        this.socket = socket;
        this.outToClient = new DataOutputStream(socket.getOutputStream());
        this.scanner = new Scanner(System.in);
    }

    public void send(String message) throws IOException {
        outToClient.writeBytes(message + "\n");
        outToClient.flush();
    }

    public void run(){
        try {
            while (true){
                String message = scanner.nextLine();
                if(message.equalsIgnoreCase("quit")){
                    socket.close();
                    break;
                }
                send(message);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
