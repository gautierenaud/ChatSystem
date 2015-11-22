package main;

import java.net.*;
import java.io.File;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import common.*;
import common.Message.MsgType;
import gui.*;
/*
 * Ajouter un filtre sur les hello, filtrer son propre nom 
 * completer switch case
 */

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
		
		receiveMessage(new Message(MsgType.TEXT_MESSAGE, "test", "toto"), InetAddress.getLoopbackAddress());

		receiveMessage(new Message(MsgType.TEXT_MESSAGE, "test2", "toto"), InetAddress.getLoopbackAddress());
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public String getUserName(){
		return userName;
	}
	//TODO : à completer, avec Renaud pour gestion creation de folder ect...
	public void receiveFile(File recFile){
		
	}
	
	public void receiveMessage(Message message, InetAddress address){
		
		String userID = message.getSender() + "@" + address.toString();
		
		// if the userID is not inside
		if (!userList.isInside(userID) && !mediator.getLocalAddresses().contains(address)){
			userList.addInstance(message.getSender(), address);
			mediator.userListUpdated();
		}
		
		switch (message.getType()){
		case BYE:

			System.out.println("received bye");

			userList.removeInstance(userID);
			mediator.userListUpdated();
			break;
		case FILE_ACCEPT:
			break;
		case FILE_REFUSE:
			break;
		case FILE_REQUEST:
			break;
		case HELLO:
			if (!mediator.getLocalAddresses().contains(address))
				mediator.sendMessage(new Message(MsgType.HELLO_REPLY, userName, userName), userList.getAddress(userID));
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
		mediator.sendMessage(msg, userList.getAddress(destinationID));
	}
	
	public void logged(String name){
		setUserName(name);
		// send Hello from NI
		mediator.sendBroadCast(new Message(Message.MsgType.HELLO, "Hello Everyone!", name));
		mediator.openUserList();
		
		// test chatbox
		/*
		ChatUserList.getInstance().AddInstance("rgautier", InetAddress.getLoopbackAddress());
		mediator.Chatbox(ChatUserList.getInstance().getUser("rgautier@" + InetAddress.getLoopbackAddress().toString()));
		*/
	}
	
	public void logOut(){
		// send Good bye
		mediator.sendBroadCast(new Message(MsgType.BYE, "Salutations!", userName));
		mediator.loggedOut();
	}
}
