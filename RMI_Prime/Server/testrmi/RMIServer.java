package testrmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    private static int PORT = 2020;
   
    public static void main(String[] args) throws Exception {
        System.out.println("Server starting...");

        //Init Registry
        Registry registry =  LocateRegistry.createRegistry(PORT);
        
        //Bind Sharing Object to Registry
        registry.bind(SharedInterface.key, new SharingObjectImplement());
        
        System.out.println("Registered \"New\" SharedInterface (SharingObjectImplement) in registry key: " + SharedInterface.key);
        System.out.println("Server started!");
    }
}
