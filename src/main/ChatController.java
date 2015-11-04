package main;

import java.net.*;

import common.*;

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
	
	// Instantiate all the different classes
	public void initAll(){
		mediator = ChatMediator.getInstance();
		mediator.Log();
		
		// for test purpose
		for (int i = 0; i < 20; i++){
			ChatUserList.getInstance().AddInstance(i + "", InetAddress.getLoopbackAddress());
		}
	}
	
	public void SetUserName(String name){
		userName = name;
	}
	
	public String GetUserName(){
		return userName;
	}
	
	public void ReceiveMessage(Message message, InetAddress address){
		
		String userID = message.getSender() + "@" + address.toString();
		
		// if the userID is not inside
		if (!ChatUserList.getInstance().IsInside(userID)){
			ChatUserList.getInstance().AddInstance(message.getSender(), address);
			mediator.UserListUpdated();
		}
		
		switch (message.getType()){
		case BYE:
			ChatUserList.getInstance().removeInstance(userID);
			
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
			break;
		default:
			break;
		}
	}
	
	public void SendMessage(String message){
	}
	
	public void Logged(String name){
		SetUserName(name);
		// send Hello from NI
		mediator.OpenUserList();
	}
}
