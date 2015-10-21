import java.io.*;
import java.net.*;

import common.Message;


public class UDPReceiver extends Thread{
	
	private DatagramSocket receiver; 
	private byte[] buf = new byte[1500]; ; 
	
	
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
		ObjectInputStream ois;
		try {
			this.receiver.receive(packet);
			//System.out.println(packet.getAddress());
			ByteArrayInputStream bais = new ByteArrayInputStream(buf);
			ois = new ObjectInputStream(bais);
			try {
				Message msgRecu = (Message) ois.readObject();
				System.out.println(msgRecu + packet.getAddress().toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
