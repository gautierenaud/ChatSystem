package main;

import gui.*;

public class ChatMediator {

	
	private static ChatGUI gui;
	private static ChatSystem system;
	
	private static ChatMediator instance;

	private ChatMediator() {
	}

	public static ChatMediator getInstance() {
		if (instance == null)
			instance = new ChatMediator();
		return instance;
	}
	
	
	
}
