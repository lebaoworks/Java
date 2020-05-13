package testtcp_chat;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    private ServerSocket server_socket;
    
    public Server(int port) throws Exception {
        this.server_socket = new ServerSocket(port);
    }

    public void start() throws Exception {
        System.out.println("[+] Waiting for an user...");
        
        Socket client_socket = this.server_socket.accept();
        System.out.println("Accepted client " + client_socket.getInetAddress().getHostName());
        handle(client_socket);
    }
    
    public void handle(Socket client_socket) throws Exception {
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client_socket.getOutputStream()));
        BufferedReader is = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        BufferedReader standard_is = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            String received_message = is.readLine();
            System.out.println("[+] Receive message: "+received_message);
            if (received_message.equals("EXIT"))
                return;
            
            System.out.print("[+] Send message: ");
            String message = standard_is.readLine();
            os.write(message+"\n");
            os.flush();
            if (message.equals("EXIT"))
                return;
            
        }
    }
    
     public static void main(String args[]) throws Exception {
        Server server = new Server(9999);
        server.start();
    }
}
