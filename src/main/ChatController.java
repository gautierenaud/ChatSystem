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
	//TODO : a completer, avec Renaud pour gestion creation de folder ect...
	public void receiveFile(File recFile){
		
	}
	
	public void receiveMessage(Message message, InetAddress address){
		
		if (userName != null){
			
			String userID = message.getSender() + "@" + address.toString();
		
			switch (message.getType()){
				case BYE:
					// efface les messages de l'utilisateur
					mediator.clearMessages(userID);
					
					userList.removeInstance(userID);
					mediator.userListUpdated();
					break;
				case FILE_ACCEPT:
					// écouter sur le port voulu, puis y envoyer la sauce!
					String key = userID + message.getContent();
					File tmpFile = requestFromUser.getFile(key);
					ChatUserInfo tmpInfo = requestFromUser.getUserInfo(key);
					// TODO: initier le transfer de messages via TCP
					break;
				case FILE_REFUSE:
					// enlever le file correspondant de la liste
					requestFromUser.removeInstance(userID + message.getContent());
					// notifier l'utilisateur?
					break;
				case FILE_REQUEST:
					requestFromOther.addRequest(message.getContent(), message.getFileSize(), userID);
					break;
				case HELLO:
					// if this application is not the source, add the user
					if (!mediator.getLocalAddresses().contains(address)){
						userList.addInstance(message.getSender(), address);
						mediator.userListUpdated();
					}
					
					if (!mediator.getLocalAddresses().contains(address) && (userName != null))
						mediator.sendMessage(new Message(MsgType.HELLO_REPLY, userName, userName), userList.getAddress(userID));
					break;
				case HELLO_REPLY:
					// if this application is not the source, add the user
					if (!mediator.getLocalAddresses().contains(address)){
						userList.addInstance(message.getSender(), address);
						mediator.userListUpdated();
					}
					System.out.println("hello");
					break;
				case TEXT_MESSAGE:
					// if this application is not the source, add the user
					if (!mediator.getLocalAddresses().contains(address)){
						userList.addInstance(message.getSender(), address);
						mediator.userListUpdated();
					}
					
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
		requestFromUser.eraseRequestList();
		requestFromOther.clearAll();
		
		mediator.clearAll();
	}
	
	public void fileRequestAnswer(boolean answer, String path, String fileName, String destinationID){
		if (answer){
			// TODO: ouvrir le port TCP et télécharger le fichier au path correspondant (path + fileName)
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
