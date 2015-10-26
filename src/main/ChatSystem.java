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
	private static ChatMediator mediator;
	
	// instanciate all the different classes
	private void initAll(){
		
		// instanciate all the different components
		
		controller = ChatController.getInstance();
		messageNI = MessageNI.getInstance();
		chatGUI = ChatGUI.getInstance();
		mediator = mediator.getInstance();
	}
	
	
	public static void main(String[] args) {
		
		
	}

}
