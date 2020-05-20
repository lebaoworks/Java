package testtcp_checkprime;

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
    public boolean isPrime(int number)
    {
        if (number<2)
            return false;
        for (int i=2; i<=Math.sqrt(number); i++)
            if (number%i == 0)
                return false;
        return true;
    }
    public void handle(Socket ClientSocket) throws Exception {
        BufferedReader is = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(ClientSocket.getOutputStream()));
 
        String received_int_str = is.readLine();
        System.out.println("Received: "+received_int_str);
        try {
            int integer = Integer.parseInt(received_int_str);
        
            if (isPrime(integer))
                os.write("Is Prime\n");
            else
                os.write("Is Not Prime\n");
        }
        catch (NumberFormatException e) {
            os.write("Is Not Number\n");
        }
        os.flush();  
    }
    
     public static void main(String args[]) throws Exception {
        Server server = new Server(9999);
        server.start();
    }
}
