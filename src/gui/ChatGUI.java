package gui;

import main.ChatMediator;

// ceci est un chat gui
//non ceci est une tata
public class ChatGUI {
	
	private static ChatGUI instance;

	private ChatGUI() {
		initGUI();
		
		//OpenChatbox("rgautier");
	}

	public static ChatGUI getInstance() {
		if (instance == null)
			instance = new ChatGUI();
		return instance;
	}
	
	private GUIView view;
	private GUIModel model;
	
	private void initGUI(){
		view = GUIView.getInstance(this);
		model = GUIModel.getInstance(this);
	}
	
	public void ShowMessage(String msg, String usr){
		System.out.println(msg + " from: " + usr);
	}
	
	public void OpenLogin(){
		view.OpenLoginWindow();
	}
	
	public void OpenChatbox(String name){
		view.OpenChatbox(name);
	}
	
	public void UserLogged(String username){
		// give the username to the controller
		ChatMediator.getInstance().Logged(username);
	}
	
	public String GetUserName(){
		return ChatMediator.getInstance().GetUserName();
	}
	
	public void OpenUserList(){
		view.OpenUserList();
	}
}
