package network;
import java.io.*;
import java.net.*;


public class UDPReceiver extends Thread{
	
	private DatagramSocket receiver; 
	private byte[] buf = new byte[1500]; 
	
	
	public UDPReceiver(){
		try {
			this.receiver = new DatagramSocket(2042);
		} catch (SocketException e) {

			e.printStackTrace();
		}
		this.start();
	}
	
	public void receive(){
		DatagramPacket packet = new DatagramPacket(buf, buf.length);	
		try {
			this.receiver.receive(packet);
			MessageNI.getInstance().addPacketBuff(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		while (true){
			this.receive();
		}
	}

}
