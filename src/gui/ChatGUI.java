package gui;

// ceci est un chat gui
//non ceci est une tata
public class ChatGUI {
	
	private static ChatGUI instance;

	private ChatGUI() {
	}

	public static ChatGUI getInstance() {
		if (instance == null)
			instance = new ChatGUI();
		return instance;
	}
	
	public void ShowMessage(String msg, String usr){
		System.out.println(msg + " from: " + usr);
	}
	
}
