package main;

import gui.*;
import network.*;

public class ChatMediator {

	private static ChatGUI gui;
	private static ChatController control;
	private static NetworkInterface network;
	
	private static ChatMediator instance;

	private ChatMediator(){
		
		gui = ChatGUI.getInstance();
		control = ChatController.getInstance();
		//network = NetworkInterface.getInstance();
		
	}

	public static ChatMediator getInstance(){
		if (instance == null)
			instance = new ChatMediator();
		return instance;
	}
	
	// methods to communicate between each class
	public void DisplayMessage(String msg, String usr){
		gui.ShowMessage(msg, usr);
	}
	
	public void Log(){
		gui.OpenLogin();
	}
	
	public void Logged(String name){
		control.Logged(name);
	}
	
	public String GetUserName(){
		return control.GetUserName();
	}
	
	public void Chatbox(String name){
		gui.OpenChatbox(name);
	}
	
	public void OpenUserList(){
		gui.OpenUserList();
	}
	
	public void UserListUpdated(){
		gui.UserListUpdated();
	}
}
