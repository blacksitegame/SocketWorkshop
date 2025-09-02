import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class UDPDNS {
    public static void main(String[] args) throws Exception {
        DatagramSocket welcomSocket = new DatagramSocket(7778);
        byte[] receiveBuffer = new byte[1024];
        while(true) {
            System.out.println("Serveren venter på klient");
            DatagramPacket requestPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            welcomSocket.receive(requestPacket);
            UDPDNSThread thread = new UDPDNSThread(welcomSocket);
            thread.start();
        }
    }
}
