package main;

public class ChatController {

	// singleton pattern for ChatController
	private static ChatController instance;
	private ChatController(){}

	public static ChatController getInstance() {
		if (instance == null)
			instance = new ChatController();
		return instance;
	}
}
