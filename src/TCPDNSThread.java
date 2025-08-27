import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class TCPDNSThread extends Thread{

    private Socket socket;
    private BufferedReader inFromClient;
    private PrintWriter outToClient;
    private HashMap<String, String> CallNameIps = new HashMap<>();
    public TCPDNSThread(Socket socket) throws IOException{
        System.out.println("thread started");
        this.socket = socket;
        this.inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outToClient = new PrintWriter(socket.getOutputStream(), true);

        CallNameIps.put("Jonas", "10.10.131.169 6789 \n");
        CallNameIps.put("Rasmus", "10.10.130.132 6789 \n");
        CallNameIps.put("Victor", "10.10.132.157 6789 \n");
    }

    @Override
    public void run() {
        try {
            String callId = inFromClient.readLine();
            if (callId != null) {
                outToClient.println(getIP(callId));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getIP(String CallId){
        String result = "Call id doesnt match IP in this Name Service";
        if (!CallNameIps.containsKey(CallId)){
            return result;
        }
        return CallNameIps.get(CallId);
    }
}
