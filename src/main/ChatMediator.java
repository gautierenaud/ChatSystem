package main;


import java.net.InetAddress;
import java.util.ArrayList;

import common.*;
import gui.*;
import network.*;

public class ChatMediator {

	private static ChatGUI gui;
	private static ChatController controller;
	private static ChatNI network;
	
	private static ChatMediator instance;

	private ChatMediator(){
		
		gui = ChatGUI.getInstance();
		controller = ChatController.getInstance();
		network = ChatNI.getInstance();
	}

	public static ChatMediator getInstance(){
		if (instance == null){
			instance = new ChatMediator();
			gui.setMediator(instance);
		}
		return instance;
	}
	
	// methods to communicate between each class
	public void displayMessage(String msg, String usr){
		gui.showMessage(msg, usr);
	}
	
	public void log(){
		gui.openLogin();
	}
	
	public void logged(String name){
		controller.logged(name);
	}
	
	public void logOut(){
		controller.logOut();
	}
	
	public void loggedOut(){
		gui.loggedOut();
	}
	
	public void sendBroadCast(Message msg){
		network.sendBroadCast(msg);
	}

	public String getUserName() {
		return controller.getUserName();
	}
	
	public void openChatbox(ChatUserInfo info){
		gui.openChatbox(info);
	}
	
	public void openUserList(){
		gui.openUserList();
	}
	
	public void userListUpdated(){
		gui.userListUpdated();
	}
	
	public void createMessage(String destinationID, MessageStruct message){
		controller.createMessage(destinationID, message);
	}
	
	public void messageReceived(Message msg, InetAddress addr){
		controller.receiveMessage(msg, addr);
	}
	
	public void updateMessage(Message msg, String id){
		gui.updateMessage(msg, id);
	}
	
	public void sendMessage(Message msg, InetAddress addr){
		network.sendMessage(msg, addr);
	}
	
	public ArrayList<InetAddress> getLocalAddresses(){
		return network.getLocalAddresses();
	}
}
