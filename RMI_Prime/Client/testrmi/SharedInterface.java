package testrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SharedInterface extends Remote{
    public static String key = "SharedObject";
    
    public boolean isPrime(int x) throws RemoteException;    
}
