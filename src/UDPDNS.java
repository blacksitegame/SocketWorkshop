import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class UDPDNS {
    public static void main(String[] args) throws Exception {
        DatagramSocket welcomSocket = new DatagramSocket(7777);
        while(true) {
            System.out.println("Serveren venter på klient");
            UDPDNSThread thread = new UDPDNSThread(welcomSocket);
            thread.start();
        }
    }
}
