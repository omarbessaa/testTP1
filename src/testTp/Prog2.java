package testTp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Prog2 {

	public static void main(String[] args) {

		final int portBessaa = 20999;
		// InetAddress ipa = InetAddress.getLocalHost();

		try {
			
			DatagramSocket dsBessaa = new DatagramSocket(portBessaa);
			System.out.println("Prog2>> Server UDP is on...");
			// the buffer
			byte[] buf = new byte[1024];
			// to receive the packet 1
			DatagramPacket dp1Bessaa;
			// to receive the packet 2
			DatagramPacket dp2Bessaa;

			// to receive packet 1
			dp1Bessaa = new DatagramPacket(buf, buf.length);
			// receive N
			dsBessaa.receive(dp1Bessaa);

			// to receive packet 2
			dp2Bessaa = new DatagramPacket(buf, buf.length);
			// receive M
			dsBessaa.receive(dp2Bessaa);

			String strNBessaa = new String(dp1Bessaa.getData());
			int NBessaa = Integer.parseInt(strNBessaa.trim());
			
			String strMBessaa = new String(dp2Bessaa.getData());
			int MBessaa = Integer.parseInt(strMBessaa.trim());
			
			System.out.println("Prog2>> N = "+ NBessaa + " M = " + MBessaa);
			
			// calculate S : S = N+M
			int S = NBessaa + MBessaa;
			
			// **************** send S to P0 using RMI
			
			Registry regClient = LocateRegistry.getRegistry("localhost", 1099);
			InterfaceP0P1 i = (InterfaceP0P1) regClient.lookup("rmiProg0");
			System.out.println("Prog2>> Sending the S = " + S + " to prog0!");
			i.sendS(S);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
