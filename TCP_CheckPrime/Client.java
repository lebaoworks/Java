package testtcp_checkprime;

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
        
        System.out.print("Input an integer: ");
        String int_str = standard_is.readLine();
        os.write(int_str+"\n");
        os.flush();
            
        System.out.println("[+] "+int_str+" "+is.readLine());
    }
    
    public static void main(String args[]) throws Exception {
        Client client = new Client("localhost",9999);
        client.start();
    }
}

