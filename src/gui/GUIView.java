package gui;

import main.*;
import java.util.*;

// generate an output presentation based on the model

public class GUIView {

	private static GUIView instance;
	private ChatGUI controller;
	private ChatMediator mediator;
	private HashMap<String, ViewChatBox> chatBox;
	
	private GUIView(ChatGUI chatGUI) {
		controller = chatGUI;
		chatBox = new HashMap<String, ViewChatBox>();
	}

	public static GUIView getInstance() {
		if (instance == null)
			instance = new GUIView(ChatGUI.getInstance());
		return instance;
	}
	
	public static GUIView getInstance(ChatGUI chatGUI) {
		if (instance == null)
			instance = new GUIView(chatGUI);
		return instance;
	}
	
	public void OpenLoginWindow(){
		ViewLogin login = new ViewLogin();
	}
	
	public void OpenChatbox(ChatUserInfo info){
		//multiton pattern
		if (!IsChatOpen(info.getUserID())){
			chatBox.put(info.getUserID(), new ViewChatBox(info));
		}
	}
	
	public ViewChatBox GetChatBox(String id){
		return chatBox.get(id);
	}
	
	public boolean IsChatOpen(String id){
		return chatBox.containsKey(id);
	}
	
	public void CloseChatBox(String id){
		chatBox.remove(id);
	}
	
	private ViewUserList userList;
	public void OpenUserList(){
		userList = ViewUserList.getInstance();
	}
	
	public void CloseUserList(){
		if (userList != null)
			userList.dispose();
	}
	
	public void UpdateUserList(){
		// the userList window is opened
		if (userList != null){
			userList.Update(ChatUserList.getInstance().GetUserList());
		}
	}
	
	public void SetMediator(ChatMediator mediator){
		this.mediator = mediator;
	}
}
