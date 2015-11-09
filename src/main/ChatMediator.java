package main;

import gui.*;
import network.*;

public class ChatMediator {

	private static ChatGUI gui;
	private static ChatController controller;
	private static NetworkInterface network;
	
	private static ChatMediator instance;

	private ChatMediator(){
		
		gui = ChatGUI.getInstance();
		controller = ChatController.getInstance();
		//network = NetworkInterface.getInstance();
	}

	public static ChatMediator getInstance(){
		if (instance == null){
			instance = new ChatMediator();
			gui.SetMediator(instance);
		}
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
		controller.Logged(name);
	}
	
	public void LogOut(){
		controller.LogOut();
	}
	
	public void LoggedOut(){
		gui.LoggedOut();
	}
	public String GetUserName(){
		return controller.GetUserName();
	}
	
	public void Chatbox(ChatUserInfo info){
		gui.OpenChatbox(info);
	}
	
	public void OpenUserList(){
		gui.OpenUserList();
	}
	
	public void UserListUpdated(){
		gui.UserListUpdated();
	}
	
	public void SendMessage(String destinationID, MessageStruct message){
		controller.SendMessage(destinationID, message);
	}
}
