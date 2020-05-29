package testrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SharingObjectImplement extends UnicastRemoteObject implements SharedInterface {
    public SharingObjectImplement() throws RemoteException {
        super();
    }
    @Override
    public boolean isPrime(int x)
    {
        for (int i=2; i<x; i++)
            if (x%i==0)
                return false;
        return true;
    }
}
