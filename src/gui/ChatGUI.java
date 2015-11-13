package gui;

import main.*;
import common.*;

// ceci est un chat gui
public class ChatGUI {
	
	private static ChatGUI instance;

	private ChatGUI() {
		initGUI();
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
	
	public void setMediator(ChatMediator mediator){
		this.mediator = mediator;
	}
	
	public void showMessage(String msg, String usr){
		System.out.println(msg + " from: " + usr);
	}
	
	public void openLogin(){
		view.openLoginWindow();
	}
	
	public void openChatbox(ChatUserInfo info){
		view.openChatbox(info);
	}
	
	public void userLogged(String username){
		// give the username to the controller
		view.closeLoginWindow();
		mediator.logged(username);
	}
	
	public void openUserList(){
		view.openUserList();
	}
	
	public void closeUserList(){
		view.closeUserList();
	}
	
	public String getUserName(){
		return mediator.getUserName();
	}
	
	public void userListUpdated(){
		view.updateUserList();
	}
	
	public void logOut(){
		mediator.logOut();
	}
	
	public void loggedOut(){
		view.closeUserList();
		view.openLoginWindow();
	}
	
	public void sendMessage(String msg, String opponentID){
		String userName = mediator.getUserName();
		MessageStruct message = new MessageStruct(userName, msg);
		model.addMessage(opponentID, message);
		mediator.createMessage(opponentID, message);
	}
	
	public void updateMessage(Message msg, String id){
		model.addMessage(id, new MessageStruct(msg.getSender(), msg.getContent()));
	}
}
