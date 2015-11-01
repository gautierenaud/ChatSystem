package gui;

// generate an output presentation based on the model

public class GUIView {

	private static GUIView instance;
	private ChatGUI controller;

	private GUIView(ChatGUI chatGUI) {
		controller = chatGUI;
	}

	public static GUIView getInstance(ChatGUI chatGUI) {
		if (instance == null)
			instance = new GUIView(chatGUI);
		return instance;
	}
	
	public void OpenLoginWindow(){
		//new Thread(new ViewLogin()).start();
		ViewLogin login = new ViewLogin();
	}
	
	public void OpenChatbox(String name){
		ViewChatbox chatbox = ViewChatbox.getInstance(name);
	}
	
	public void OpenUserList(){
		ViewUserList userList = ViewUserList.getInstance();
	}
}
