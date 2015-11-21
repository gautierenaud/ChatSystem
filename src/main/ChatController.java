package main;

import java.net.*;

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
		userList = ChatUserList.getInstance();
		
		mediator = ChatMediator.getInstance();

		userList.init();
		mediator.log();
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void receiveMessage(Message message, InetAddress address){
		
		String userID = message.getSender() + "@" + address.toString();
		
		// if this application is not the source
		if (!mediator.getLocalAddresses().contains(address)){
			userList.addInstance(message.getSender(), address);
			mediator.userListUpdated();
		}
		
		switch (message.getType()){
		case BYE:
			userList.removeInstance(userID);
			mediator.userListUpdated();
			break;
		case FILE_ACCEPT:
			break;
		case FILE_REFUSE:
			break;
		case FILE_REQUEST:
			String title = message.getSender();
			if (message.getContent() != ""){
				title += message.getContent();
			}
			mediator.fileRequestQuery(title, userID);
			break;
		case HELLO:
			if (!mediator.getLocalAddresses().contains(address) && (userName != null))
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
	}
	
	public void logOut(){
		// send Good bye
		exit();
		mediator.loggedOut();

		userList.init();
		mediator.log();
		userList.eraseUserList();
	}
	
	public void exit(){
		mediator.sendBroadCast(new Message(MsgType.BYE, "Salutations!", userName));
		userName = "";
	}
	
	public void fileRequestAnswer(boolean ans, String filePath, String destinationID){
		if (ans){
			mediator.sendMessage(new Message(MsgType.FILE_ACCEPT, "file accepted"), userList.getAddress(destinationID));
			// open TCP port, should we put the port number in the content of the message?
		}else
			mediator.sendMessage(new Message(MsgType.FILE_REFUSE, "file refused"), userList.getAddress(destinationID));
	}
}
