package network;

import java.io.*;
import java.net.*;

public class UDPSender {

	private DatagramSocket senderSocket;
	
	public UDPSender(){
		try {
			senderSocket = new DatagramSocket(2041);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void send(DatagramPacket packet){
		try {
			senderSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
