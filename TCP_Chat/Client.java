package testtcp_chat;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    Socket client_socket = null;
       
    public Client(String server_address, int server_port) throws Exception {
        client_socket = new Socket(server_address, server_port);
    }
    public void start() throws Exception {
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(client_socket.getOutputStream()));
        BufferedReader is = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        BufferedReader standard_is = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            System.out.print("[+] Send message: ");
            String message = standard_is.readLine();
            os.write(message+"\n");
            os.flush();
            if (message.equals("EXIT"))
                return;
            
            String received_message = is.readLine();
            System.out.println("[+] Receive message: "+received_message);
            if (received_message.equals("EXIT"))
                return;
        }
    }
    
    public static void main(String args[]) throws Exception {
        Client client = new Client("localhost",9999);
        client.start();
    }
}

