package network;

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
	private UDPSender udpSender;
	private UDPReceiver udpReceiver;

	
	private MessageNI(){
		messageStack = new Stack<Message>();
		
		// initialising the sockets
		udpSender = new UDPSender();
		udpReceiver = new UDPReceiver();
		udpReceiver.start();
		
	}

	public void addMsgBuff(Message msg){
		messageStack.push(msg);
	}
}
