package testtcp_checkpass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private ServerSocket server_socket;
    private String password;
    
    public Server(int port, String password) throws Exception {
        this.server_socket = new ServerSocket(port);
        this.password = password;
        
    }

    public void start() throws Exception {
        System.out.println("[+] Waiting for an user...");
        
        Socket client_socket = this.server_socket.accept();
        System.out.println("Accepted client " + client_socket.getInetAddress().getHostName());
        if (handle(client_socket))
            System.out.println("\t[+] Client have passed");
        else
            System.out.println("\t[-] Client have not passed");
    }
    
    public boolean handle(Socket ClientSocket) throws Exception {
        BufferedReader is = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream()));
 
        for (int i=0; i<4; i++) {
            String received_password = is.readLine(); 
            System.out.println("Received: "+received_password);
            if (received_password.equals(password)) {
                os.write("Correct\n");
                os.flush();
                return true;
            }
            os.write("Incorrect\n");
            os.flush();  
        }
        return false;
    }
    
     public static void main(String args[]) throws Exception {
        Server server = new Server(9999,"123456");
        server.start();
    }
}
