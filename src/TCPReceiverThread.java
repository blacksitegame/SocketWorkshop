import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPReceiverThread extends Thread{
    private Socket socket;

    public TCPReceiverThread(Socket socket) {
        this.socket = socket;
    }

    public String receive() throws IOException {
        String clientSentence;

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientSentence = inFromClient.readLine();
        return clientSentence;
    }

    @Override
    public void run() {
        try {
            System.out.println(receive());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
