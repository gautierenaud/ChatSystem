package network;

import java.io.*;
import java.net.*;
import common.Message;

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
	
	public void sendMsg(Message msg, InetAddress address){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(msg);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] buf= baos.toByteArray();
		DatagramPacket packet;
		
		try {
			packet = new DatagramPacket(buf, buf.length, address, 2042);
			senderSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Msg sent");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UDPSender sender = new UDPSender();
		UDPReceiver receiver = new UDPReceiver();
		Message msg = new Message(Message.MsgType.HELLO, "Hello les fillettes!");
		try {
			sender.sendMsg(msg, InetAddress.getByName("10.1.255.255"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
