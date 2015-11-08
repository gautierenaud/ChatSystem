package gui;

import main.*;

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
	private ChatMediator mediator;
	
	private void initGUI(){
		view = GUIView.getInstance(this);
		model = GUIModel.getInstance(this);
	}
	
	public void SetMediator(ChatMediator mediator){
		this.mediator = mediator;
	}
	
	public void ShowMessage(String msg, String usr){
		System.out.println(msg + " from: " + usr);
	}
	
	public void OpenLogin(){
		view.OpenLoginWindow();
	}
	
	public void OpenChatbox(ChatUserInfo info){
		view.OpenChatbox(info);
	}
	
	public void UserLogged(String username){
		// give the username to the controller
		mediator.Logged(username);
	}
	
	public String GetUserName(){
		return mediator.GetUserName();
	}
	
	public void OpenUserList(){
		view.OpenUserList();
	}
	
	public void UserListUpdated(){
		view.UpdateUserList();
	}
	
	public void LogOut(){
		mediator.LogOut();
	}
	
	public void LoggedOut(){
		view.CloseUserList();
		view.OpenLoginWindow();
	}
	
	public void SendMessage(String msg, String opponentID){
		String userName = mediator.GetUserName();
		MessageStruct message = new MessageStruct(userName, msg);
		model.AddMessage(opponentID, message);
		mediator.SendMessage(opponentID, message);
	}
}
