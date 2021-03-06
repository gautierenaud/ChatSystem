package gui;

import main.*;
import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

// generate an output presentation based on the model

public class GUIView {

	private static GUIView instance;
	private ChatGUI controller;
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
	
	private ViewLogin login;
	public void openLoginWindow(){
		if (login == null)
			login = new ViewLogin();
	}
	
	public void closeLoginWindow(){
		login.dispose();
		login = null;
	}
	
	public void openChatbox(ChatUserInfo info){
		//multiton pattern
		if (!isChatOpen(info.getUserID())){
			chatBox.put(info.getUserID(), new ViewChatBox(info));
		}
	}
	
	public ViewChatBox getChatBox(String id){
		return chatBox.get(id);
	}
	
	public boolean isChatOpen(String id){
		return chatBox.containsKey(id);
	}
	
	public void closeChatBox(String id){
		chatBox.remove(id);
	}
	
	private ViewUserList userList;
	public void openUserList(){
		userList = new ViewUserList();
	}
	
	public void closeUserList(){
		if (userList != null)
			userList.dispose();
		userList = null;
	}
	
	public void updateUserList(){
		// the userList window is opened
		if (userList != null){
			userList.updateList();
		}
	}
	
	public void messageReceivedNotification(String id){
		// test if the chatbox window is opened
		if (userList != null){
			if (!isChatOpen(id)){
				ChatUserList.getInstance().getUser(id).incrementUnreadCount();
				userList.updateList();
			}
		}
			
		// if not, add count to unread message and update UserList
	}
	
	public synchronized void fileRequestQuery(String fileName, float fileSize, String destinationID){
		// maybe test if an user windows is opened
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle(fileName + ": " + fileSize + " bytes");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION)
			controller.fileRequestAnswer(true, fileChooser.getSelectedFile().getPath(), fileName, destinationID);
		else
			controller.fileRequestAnswer(false, "", fileName, destinationID);
	}
	
	public void sendFile(File[] fileList, String destinationID){
		controller.sendFile(fileList, destinationID);
	}
	
	public void clearAll(){
		// close all the opened chatboxes
		for (ViewChatBox box : chatBox.values()){
			box.closeBox();
		}
		chatBox.clear();

		// close the UserList
		userList.dispose();
	}
}
