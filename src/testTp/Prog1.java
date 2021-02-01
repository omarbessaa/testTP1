package testTp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Prog1 {

	public static void main(String[] args) {
		
		final int portBessaa = 20004;
		ServerSocket listenSocketBessaa;
		Socket conP0Bessaa ;
		
		DataInputStream receiveBessaa;
		DataOutputStream sendBessaa;
		
		int NBessaa;
		int MBessaa;
		
		try {
			listenSocketBessaa = new ServerSocket(portBessaa);
			System.out.println("Prog1>> Server TCP is on...");
			conP0Bessaa = listenSocketBessaa.accept();
			System.out.println("Prog1>> Connection established with client Prog0 ! ");
			
			// setting up the streams
			receiveBessaa = new DataInputStream(conP0Bessaa.getInputStream());
			
			// receive the number
			NBessaa = (int) receiveBessaa.readInt();
			System.out.println("Prog1>> received : " + NBessaa + " from the client Prog0 !");
			
			//**********************
			// Read M and send it to P2
			System.out.println("Prog1>> Enter the number M : ");
			Scanner kyBessaa = new Scanner(System.in);
			MBessaa = kyBessaa.nextInt();
			
			//********************** Send M and N to prog 2 using UDP
			
			final int portP2Bessaa = 20999; 
			
			try {
				
				InetAddress ipaBessaa = InetAddress.getLocalHost();
				DatagramSocket conP2Bessaa = new DatagramSocket();
				// the packet to send
				DatagramPacket dp1Bessaa;
				byte[] buf1Bessaa = new byte[1024];
				byte[] buf2Bessaa = new byte[1024];
				
				//convert the number N to byte
				buf1Bessaa = (NBessaa + "").getBytes();
				// packet 1 : send N
				dp1Bessaa = new DatagramPacket(buf1Bessaa, buf1Bessaa.length, ipaBessaa, portP2Bessaa);
				// send N
				conP2Bessaa.send(dp1Bessaa);
				
				//convert the number M to byte
				buf2Bessaa = (MBessaa + "").getBytes();
				// packet 2 : send M
				DatagramPacket dp2Bessaa = new DatagramPacket(buf2Bessaa, buf2Bessaa.length, ipaBessaa, portP2Bessaa);
				// send N
				conP2Bessaa.send(dp2Bessaa);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

