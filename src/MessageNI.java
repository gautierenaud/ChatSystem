
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
	
	
	
	private DatagramSocket localSocket;
	
	public MessageNI(){
		messageStack = new Stack<Message>();
	}

	public void addMsgBuff(Message msg){
		messageStack.push(msg);
	}
}
