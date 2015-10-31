package gui;

// generate an output presentation based on the model

public class GUIView {

	private ChatGUI controller;
	
	public GUIView(ChatGUI chatGUI){
		controller = chatGUI;
	}
	
	public void OpenLoginWindow(){
		//new Thread(new ViewLogin()).start();
		ViewLogin login = new ViewLogin();
	}
	
	public void OpenChatbox(String name){
		ViewChatbox chatbox = ViewChatbox.getInstance(name);
	}
	
}
