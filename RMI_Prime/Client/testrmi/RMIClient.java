package testrmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    private static final String HOST = "localhost";
    private static final int PORT = 2020;
   
    public static void main(String[] args) throws Exception {
        //Get Registry
        Registry registry = LocateRegistry.getRegistry(HOST, PORT);
        
        //Get SharedObject
        SharedInterface received_obj = (SharedInterface) registry.lookup(SharedInterface.key);

        System.out.print("50 is prime:  " + received_obj.isPrime(50));
    }
    
}
