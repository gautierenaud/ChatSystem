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
	private String userName;
	
	// Instantiate all the different classes
	public void initAll(){
		mediator = ChatMediator.getInstance();
		mediator.Log();
	}
	
	public void SetUserName(String name){
		userName = name;
	}
	
	public String GetUserName(){
		return userName;
	}
	
	public void ReceiveMessage(String source, String message){
		
	}
	
	public void SendMessage(String message){
	}	
}
