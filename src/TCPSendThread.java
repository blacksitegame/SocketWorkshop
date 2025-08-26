import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPSendThread extends Thread{
    private Socket socket;
    private String message;

    public TCPSendThread(Socket socket) {
        this.socket = socket;
    }

    public void send() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // auto-flush on println
        Scanner scanner = new Scanner(System.in);

        while (!socket.isClosed()) {
            String line = scanner.nextLine();
            out.println(line); // sends line with newline and flushes
            System.out.println(line);
            if ("exit".equalsIgnoreCase(line)) {
                socket.close();
                break;
            }
        }
    }

    public void run(){
        try {
            send();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
