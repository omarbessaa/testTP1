package testTp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceP0P1 extends Remote {

	public void sendS(int S) throws RemoteException;
}
