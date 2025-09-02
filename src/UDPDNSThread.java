import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

public class UDPDNSThread extends Thread {
    private final DatagramSocket socket;
    private HashMap<String, String> CallNameIps = new HashMap<>();

    public UDPDNSThread(DatagramSocket socket) throws IOException {
        System.out.println("UDP thread started");
        this.socket = socket;

        CallNameIps.put("Jonas", "10.10.131.169 6789 \n");
        CallNameIps.put("Rasmus", "10.10.130.100 6789 \n");
        CallNameIps.put("Victor", "10.10.132.157 6789 \n");
    }

    @Override
    public void run() {
        byte[] receiveBuffer = new byte[1024];

        while (true) {
            try {
                DatagramPacket requestPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(requestPacket);

                String callId = new String(requestPacket.getData(), 0, requestPacket.getLength()).trim();
                System.out.println("Received request for: " + callId);

                String responseMessage = getIP(callId);

                byte[] sendBuffer = responseMessage.getBytes();
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();

                DatagramPacket responsePacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                socket.send(responsePacket);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getIP(String CallId) {
        return CallNameIps.getOrDefault(CallId, "Call id doesn't match IP in the name service");
    }

    public void addUserToDNS(String user, String IPandPort){
        String cleanUser = user.trim();
        String cleanIPandPort = IPandPort.trim();

        if(CallNameIps.containsKey(user)){
            throw new IllegalArgumentException("User already exists in the list");
        }

        CallNameIps.put(cleanUser,cleanIPandPort);
    }

    public void printUserList(){
        for (String user : CallNameIps.keySet()) {
            String ipAndPort = CallNameIps.get(user);
            System.out.println(user + "->" + ipAndPort);
        }
    }

}