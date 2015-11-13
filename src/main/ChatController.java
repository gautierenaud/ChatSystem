package main;

import java.net.*;

import common.*;
import common.Message.MsgType;
import gui.*;

public class ChatController {

	// singleton pattern for ChatController
	private static ChatController instance;
	private ChatController(){
	}

	public static ChatController getInstance() {
		if (instance == null)
			instance = new ChatController();
		return instance;
	}
	
	private static ChatMediator mediator;
	private String userName;
	private ChatUserList userList;
	
	// Instantiate all the different classes
	public void initAll(){
		mediator = ChatMediator.getInstance();
		mediator.log();

		userList = ChatUserList.getInstance();
		// test user list
		for (int i = 0; i < 30; i++){
			ChatUserList.getInstance().addInstance(i + "", InetAddress.getLoopbackAddress());
		}
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void receiveMessage(Message message, InetAddress address){
		
		String userID = message.getSender() + "@" + address.toString();
		
		// if the userID is not inside
		if (!userList.isInside(userID)){
			userList.addInstance(message.getSender(), address);
			mediator.userListUpdated();
		}
		
		switch (message.getType()){
		case BYE:
			userList.removeInstance(userID);
			// mediator.SendGoodBye();
			break;
		case FILE_ACCEPT:
			break;
		case FILE_REFUSE:
			break;
		case FILE_REQUEST:
			break;
		case HELLO:
			// the send a HELLO_REPLY to this address
			break;
		case HELLO_REPLY:
			
			break;
		case TEXT_MESSAGE:
			// give the message to the GUIModel
			mediator.updateMessage(message, userID);
			break;
		default:
			break;
		}
	}
	
	// will create a message to send
	public void createMessage(String destinationID, MessageStruct message){
		Message msg = new Message(MsgType.TEXT_MESSAGE , message.getMessage(), getUserName());
		// give it to the NetworkNI
		// mediator.Send(msg, userList.GetAddress(destinationID));
	}
	
	public void logged(String name){
		setUserName(name);
		// send Hello from NI
		mediator.sendHello(new Message(Message.MsgType.HELLO, "Hello Everyone!", name));
		mediator.openUserList();
		
		// test chatbox
		/*
		ChatUserList.getInstance().AddInstance("rgautier", InetAddress.getLoopbackAddress());
		mediator.Chatbox(ChatUserList.getInstance().getUser("rgautier@" + InetAddress.getLoopbackAddress().toString()));
		*/
	}
	
	public void logOut(){
		// send Good bye
		mediator.loggedOut();
	}
}
