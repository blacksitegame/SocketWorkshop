import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPReceiverThread extends Thread {
    private Socket socket;
    private BufferedReader inFromClient;

    public TCPReceiverThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String receive() throws IOException {
        return inFromClient.readLine();
    }

    @Override
    public void run() {
        try {
            String line;

            while ((line = receive()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
