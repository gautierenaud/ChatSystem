package main;

import java.awt.TrayIcon.MessageType;
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
		mediator.Log();

		userList = ChatUserList.getInstance();
		// test user list
		for (int i = 0; i < 30; i++){
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
		if (!userList.IsInside(userID)){
			userList.AddInstance(message.getSender(), address);
			mediator.UserListUpdated();
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
			//mediator.MessageReceived();
			break;
		default:
			break;
		}
	}
	
	// will create a message to send
	public void SendMessage(String destinationID, MessageStruct message){
		Message msg = new Message(MsgType.TEXT_MESSAGE , message.getMessage(), GetUserName());
		// give it to the NetworkNI
		// mediator.Send(msg, userList.GetAddress(destinationID));
	}
	
	public void Logged(String name){
		SetUserName(name);
		// send Hello from NI
		mediator.OpenUserList();
		
		// test chatbox
		/*
		ChatUserList.getInstance().AddInstance("rgautier", InetAddress.getLoopbackAddress());
		mediator.Chatbox(ChatUserList.getInstance().getUser("rgautier@" + InetAddress.getLoopbackAddress().toString()));
		*/
	}
	
	public void LogOut(){
		// send Good bye
		mediator.LoggedOut();
	}
}
