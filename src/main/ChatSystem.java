package main;

import network.*;
import gui.*;

public class ChatSystem {

	/**
	 * @param args
	 */
	
	private static ChatController controller;
	private static MessageNI messageNI;
	private static ChatGUI chatGUI;
	
	
	public static void main(String[] args) {
		// instanciate all the different components
		
		controller = ChatController.getInstance();
		messageNI = MessageNI.getInstance();
		chatGUI = ChatGUI.getInstance();
		
	}

}
