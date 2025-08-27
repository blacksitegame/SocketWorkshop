import com.sun.tools.javac.Main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPDNS {


    public static void main(String[] args) throws Exception {
        ServerSocket welcomSocket = new ServerSocket(7777);
        Socket connectionSocket;
        while(true) {
            System.out.println("Serveren venter på klient");
            connectionSocket = welcomSocket.accept();
            TCPDNSThread thread = new TCPDNSThread(connectionSocket);
            thread.start();
        }

    }
}

