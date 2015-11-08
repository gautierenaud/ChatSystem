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
	
	public void send(DatagramPacket packet){
		try {
			senderSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
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
	}*/

}
