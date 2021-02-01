package testTp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Prog0 extends UnicastRemoteObject implements InterfaceP0P1 {

	protected Prog0() throws RemoteException {
		super();
	}

	@Override
	public void sendS(int S) throws RemoteException {
		System.out.println("Peog0>> Received from Prog 2 : " + S);
	}

	public static void main(String[] args) {
		final int portBessaa = 20004;
		Socket conP1Bessaa;

		// DataInputStream receiveBessaa;
		DataOutputStream sendBessaa;

		try {
			conP1Bessaa = new Socket("localhost", portBessaa);

			// the output stream
			sendBessaa = new DataOutputStream(conP1Bessaa.getOutputStream());

			// read the number and then send it to the server Prog1
			System.out.println("Prog0>> Enter the number N : ");
			Scanner kyBessaa = new Scanner(System.in);
			int NBessaa = kyBessaa.nextInt();

			// send N to Prog1 using TCP socket
			sendBessaa.writeInt(NBessaa);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Prog0 rmiProg2 = new Prog0();

			Registry register = LocateRegistry.createRegistry(1099); // the default port for rmi is 1099
			// bind the object to be accessed from client Prog2
			register.rebind("rmiProg0", rmiProg2);
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
