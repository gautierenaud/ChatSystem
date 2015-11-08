package network;

import java.io.*;
import java.net.*;
import java.util.*;

import common.Message;

public class MessageNI {
	
	private static MessageNI instance;
	
	public static MessageNI getInstance(){
		if(instance == null)
			instance = new MessageNI();
		return instance;
	}
	
	private Stack<Message> messageStack;
	private Stack<DatagramPacket> packetStack; 
	private UDPSender udpSender;
	private UDPReceiver udpReceiver;

	
	private MessageNI(){
		messageStack = new Stack<Message>();
		packetStack = new Stack<DatagramPacket>();
		
		// initialising the sockets
		udpSender = new UDPSender();
		udpReceiver = new UDPReceiver();
		//udpReceiver.start();
		
	}
	
	public void addPacketBuff(DatagramPacket packet){
		packetStack.push(packet);
	}

	public void addMsgBuff(Message msg){
		messageStack.push(msg);
	}
	
	public Message turnPacketToMessage(){
		DatagramPacket packet = packetStack.pop();
		Message mess= null; 
		ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
		try {
			ObjectInputStream ois = new ObjectInputStream(bais);
			mess = (Message)ois.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mess ; 
	}
	
	public DatagramPacket turnMesstoPacket(InetAddress address){
		DatagramPacket packet; 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(messageStack.pop());
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] buf= baos.toByteArray();
		packet = new DatagramPacket(buf, buf.length, address, 2042);
		return packet;
	}
	
	public void sendPacket(InetAddress address){
		udpSender.send(turnMesstoPacket(address));
	}
	
}
