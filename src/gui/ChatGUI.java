package gui;

public class ChatGUI {
	
	private static ChatGUI instance;

	private ChatGUI() {
	}

	public static ChatGUI INSTANCE() {
		if (instance == null)
			instance = new ChatGUI();
		return instance;
	}
	
}
