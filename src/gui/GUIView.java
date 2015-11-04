package gui;

import main.*;

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
	
	public void OpenChatbox(ChatUserInfo info){
		ViewChatbox chatbox = ViewChatbox.getInstance(info);
	}
	
	ViewUserList userList;
	public void OpenUserList(){
		userList = ViewUserList.getInstance();
	}
	
	public void CloseUserList(){
		userList = null;
	}
	
	public void UpdateUserList(){
		// the userList window is opened
		if (userList != null){
			userList.Update(ChatUserList.getInstance().GetUserList());
		}
	}
}
