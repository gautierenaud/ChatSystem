package main;

import java.io.IOException;

import gui.*;
import network.*;

public class ChatMediator {

	private static ChatGUI gui;
	private static ChatController control;
	private static NetworkInterface network;
	
	private static ChatMediator instance;

	private ChatMediator() throws IOException {
		
		gui = ChatGUI.getInstance();
		control = ChatController.getInstance();
		network = NetworkInterface.getInstance();
		
	}

	public static ChatMediator getInstance() throws IOException {
		if (instance == null)
			instance = new ChatMediator();
		return instance;
	}
	
	// methods to communicate between each class
	public void DisplayMessage(String msg, String usr){
		gui.ShowMessage(msg, usr);
	}
}
