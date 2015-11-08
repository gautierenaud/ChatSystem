package network;
import java.io.*;
import java.net.*;

import common.Message;


public class UDPReceiver extends Thread{
	
	private DatagramSocket receiver; 
	private byte[] buf = new byte[1500]; 
	
	
	public UDPReceiver(){
		try {
			this.receiver = new DatagramSocket(2042);
			//this.buf = new byte[100]; 
		} catch (SocketException e) {

			e.printStackTrace();
		}
		this.start();
	}
	
	public void receive(){
		DatagramPacket packet = new DatagramPacket(buf, buf.length);	
		try {
			this.receiver.receive(packet);
			//System.out.println(packet.getAddress());
			MessageNI.getInstance().addPacketBuff(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while (true){
			this.receive();
		}
	}

}
