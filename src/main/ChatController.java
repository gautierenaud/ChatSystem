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
	
	private ChatMediator mediator;
	private String userName;
	private ChatUserList userList;
	private ChatFileRequestFromUser requestFromUser;
	private ChatFileRequestFromOther requestFromOther;
	
	// Instantiate all the different classes
	public void initAll(ChatMediator mediator){
		userList = ChatUserList.getInstance();
		requestFromUser = new ChatFileRequestFromUser();
		requestFromOther = new ChatFileRequestFromOther();
		
		this.mediator = mediator;
	}
	
	public void start(){
		mediator.log();
	}
	
	public void setUserName(String name){
		userName = name;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void receiveMessage(Message message, InetAddress address){
		
		if ((userName != null) && (message.getSender() != null)){
			
			String userID = message.getSender() + "@" + address.toString();
		
			switch (message.getType()){
				case BYE:
					// efface les messages de l'utilisateur
					mediator.clearMessages(userID);
					
					userList.removeInstance(userID);
					mediator.userListUpdated();
					break;
					
				case FILE_ACCEPT:
					// on test si on nous envoie pas n'importe quoi
					String key = userID + message.getContent();
					if (requestFromUser.containsRequest(key)){
						// écouter sur le port voulu, puis y envoyer la sauce!
						File tmpFile = requestFromUser.getFile(key);
						ChatUserInfo tmpInfo = requestFromUser.getUserInfo(key);
						mediator.sendFile(tmpFile, tmpInfo.getAddress());
						// enlever la request de la liste
						requestFromUser.removeInstance(key);
					}
					break;
					
				case FILE_REFUSE:
					// enlever le file correspondant de la liste
					requestFromUser.removeInstance(userID + message.getContent());
					break;
					
				case FILE_REQUEST:
					requestFromOther.addRequest(message.getContent(), message.getFileSize(), userID);
					break;
					
				case HELLO:
					addNewUser(message, address);
					
					if (!mediator.getLocalAddresses().contains(address) && (userName != null))
						mediator.sendMessage(new Message(MsgType.HELLO_REPLY, userName, userName), userList.getAddress(userID));
					break;
				case HELLO_REPLY:
					addNewUser(message, address);
					break;
				case TEXT_MESSAGE:
					addNewUser(message, address);
					
					// give the message to the GUIModel
					if (message.getContent().length() > 0)
						mediator.updateMessage(message, userID);
					break;
				default:
					break;
			}
		}
	}
	
	// add user to the list if it is not already inside 
	private void addNewUser(Message message, InetAddress address){
		// if this application is not the source, add the user
		if (!mediator.getLocalAddresses().contains(address) && message.getSender() != ""){
			userList.addInstance(message.getSender(), address);
			mediator.userListUpdated();
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
		requestFromUser.eraseRequestList();
		requestFromOther.clearAll();
		
		mediator.clearAll();
	}
	
	public void fileRequestAnswer(boolean answer, String path, String fileName, String destinationID){
		if (answer){
			mediator.prepareToReceive(fileName, path, userList.getAddress(destinationID));
			mediator.sendMessage(new Message(MsgType.FILE_ACCEPT, fileName, userName), userList.getAddress(destinationID));
		}else
			mediator.sendMessage(new Message(MsgType.FILE_REFUSE, fileName, userName), userList.getAddress(destinationID));
	}
	
	public void sendFile(File[] fileList, String destinationID){
		// will send a request message only if 
		if (fileList != null){
			ChatUserInfo info = userList.getUser(destinationID);
			for (File file : fileList){
				requestFromUser.addInstance(file, info);
				mediator.sendMessage(new Message(MsgType.FILE_REQUEST, file.getName(), userName, file.length()), info.getAddress());
			}
		}
	}
}
