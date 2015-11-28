package main;

import network.ChatNI;
import gui.ChatGUI;

public class ChatSystem {

	/**
	 * @param args
	 */
	
	// the system is the aggregation of several components
	private static ChatController controller;
	private static ChatMediator mediator;
	private static ChatNI network;
	private static ChatGUI gui;
	
	
	public static void main(String[] args){
		// init all the components
		initAll();
		
		// start the application
		controller.start();
	}
	
	private static void initAll(){
		// create the objects
		controller = ChatController.getInstance();
		mediator = ChatMediator.getInstance();
		network = ChatNI.getInstance();
		gui = ChatGUI.getInstance();
		
		// give them the reference to the mediator etc...
		controller.initAll(mediator);
		mediator.initAll(controller, network, gui);
		network.initAll(mediator);
		gui.initAll(mediator);
	}

}
