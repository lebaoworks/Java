package testtcp_checkpass;

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
        
        for (int i=0; i<4; i++) {
            System.out.print("Try "+i+" Input password: ");
            String password = standard_is.readLine();
            os.write(password+"\n");
            os.flush();
            
            String result = is.readLine();
            if (result.equals("Correct"))
            {
                System.out.println("[+] Password: \""+password+"\" is correct");
                return;
            }
            System.out.println("[+] Password: \""+password+"\" is not correct");
        }
    }
    
    public static void main(String args[]) throws Exception {
        Client client = new Client("localhost",9999);
        client.start();
    }
}

