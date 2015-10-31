package main;

import java.io.IOException;

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
	
	// Instantiate all the different classes
	public void initAll(){
		try {
			mediator = ChatMediator.getInstance();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		mediator.DisplayMessage("Hello World!", "Renaud");
	}
	
}
