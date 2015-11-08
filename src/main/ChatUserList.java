package main;

import java.net.*;
import java.util.*;

public class ChatUserList {
	
	private static ChatUserList instance;

	private ChatUserList() {
	}

	public static ChatUserList getInstance() {
		if (instance == null)
			instance = new ChatUserList();
		return instance;
	}
	
	// HashMap for storing user's information
	private HashMap<String, ChatUserInfo> userList = new HashMap<>();
	
	public ChatUserInfo getUser(String userID){
		return userList.get(userID);
	}
	
	// look for the element
	public boolean IsInside(String userID){
		return userList.containsKey(userID);
	}
	
	// add an instance
	public synchronized void AddInstance(String username, InetAddress address){
		String tmpID = username + "@" + address.toString();		
		if (!userList.containsKey(tmpID)){
			userList.put(tmpID, new ChatUserInfo(username, address));
		}
	}
	
	// remove an instance
	public void removeInstance(String userID){
		userList.remove(userID);
	}
	
	public InetAddress GetAddress(String userID){
		if (userList.containsKey(userID)){
			return userList.get(userID).getAddress();
		}
		return null;
	}
	
	// return a list of the users
	public Vector<ChatUserInfo> GetUserList(){
		Vector<ChatUserInfo> result = new Vector<>();
		for (ChatUserInfo user : userList.values() ){
			result.addElement(user);
		}
		return result;
	}
	
	// return a list of users, containing the input string
	public Vector<ChatUserInfo> SearchUserList(String input){
		if (input == "")
			return GetUserList();
		else{
			Vector<ChatUserInfo> result = new Vector<>();
			for (ChatUserInfo user : userList.values() ){
				if (user.getUsername().contains(input))
					result.addElement(user);
			}
			return result;
		}
	}
}
