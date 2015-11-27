package main;

import java.net.*;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.io.File;

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
	private ChatFileRequestList requestList;
	
	// Instantiate all the different classes
	public void initAll(){
		userList = ChatUserList.getInstance();
		requestList = ChatFileRequestList.getInstance();
		
		mediator = ChatMediator.getInstance();

		mediator.log();
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public String getUserName(){
		return userName;
	}
	//TODO : a completer, avec Renaud pour gestion creation de folder ect...
	public void receiveFile(File recFile){
		
	}
	
	public void receiveMessage(Message message, InetAddress address){
		
		if (userName != null){
			
			String userID = message.getSender() + "@" + address.toString();
			
			// if this application is not the source
			if (!mediator.getLocalAddresses().contains(address)){
				userList.addInstance(message.getSender(), address);
				mediator.userListUpdated();
			}
			
			switch (message.getType()){
				case BYE:
					// efface les messages de l'utilisateur
					mediator.clearMessages(userID);
					
					userList.removeInstance(userID);
					mediator.userListUpdated();
					break;
				case FILE_ACCEPT:
					// écouter sur le port voulu
					break;
				case FILE_REFUSE:
					// enlever le file correspondant de la liste
					break;
				case FILE_REQUEST:
					String title = message.getSender();
					if (message.getContent() != ""){
						title = message.getContent();
					}
					String answer = mediator.fileRequestQuery(title, userID);
					MsgType ansType;
					if (answer != ""){
						// open TCP listening port? send it messages?
						
						// send accept message
						ansType = MsgType.FILE_ACCEPT;
					}else
						ansType = MsgType.FILE_REFUSE;
					
					mediator.sendMessage(new Message(ansType, message.getContent(), userName), userList.getAddress(userID));
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
		mediator.sendBroadCast(new Message(MsgType.HELLO, "Hello Everyone!", name));
		mediator.openUserList();
	}
	
	public void logOut(){
		exit();

		// reopen log box	
		mediator.log();
	}
	
	// send bye message and reinitialize all the lists
	public void exit(){
		mediator.sendBroadCast(new Message(MsgType.BYE, "Salutations!", userName));
		userName = null;
		clearAll();
	}
	
	public void clearAll(){
		userList.eraseUserList();
		requestList.eraseRequestList();
		mediator.clearAll();
	}
	
	public void fileRequestAnswer(boolean ans, String filePath, String destinationID){
		if (ans){
			mediator.sendMessage(new Message(MsgType.FILE_ACCEPT, "file accepted"), userList.getAddress(destinationID));
			// open TCP port, should we put the port number in the content of the message?
		}else
			mediator.sendMessage(new Message(MsgType.FILE_REFUSE, "file refused"), userList.getAddress(destinationID));
	}
	
	public void sendFile(File[] fileList, String destinationID){
		// will send a request message only if 
		if (fileList != null){
			ChatUserInfo info = userList.getUser(destinationID);
			for (File file : fileList){
				mediator.sendMessage(new Message(MsgType.FILE_REQUEST, file.getName(), destinationID), info.getAddress());

				requestList.addInstance(file, info);
			}
		}
	}
}
