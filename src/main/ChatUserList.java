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
	private HashMap<String, ChatUserInfo> userList;

	public void init(){
		userList = new HashMap<>();
	}
	
	public void eraseUserList(){
		userList = null;
	}
	
	public ChatUserInfo getUser(String userID){
		if (userList != null)
			return userList.get(userID);
		else
			return null;
	}
	
	// look for the element
	public boolean isInside(String userID){
		if (userList != null)
			return userList.containsKey(userID);
		else
			return false;
	}
	
	// add an instance
	public synchronized void addInstance(String username, InetAddress address){
		if (userList != null){
			String tmpID = username + "@" + address.toString();
			if (!userList.containsKey(tmpID)){
				userList.put(tmpID, new ChatUserInfo(username, address));
			}
		}
	}
	
	// remove an instance
	public void removeInstance(String userID){
		if (userList != null)
			userList.remove(userID);
	}
	
	public InetAddress getAddress(String userID){
		if (userList != null){
			if (userList.containsKey(userID)){
				return userList.get(userID).getAddress();
			}
		}
		return null;
	}
	
	// return a list of the users
	public Vector<ChatUserInfo> getUserList(){
		if (userList != null){
			Vector<ChatUserInfo> result = new Vector<>();
			for (ChatUserInfo user : userList.values() ){
				result.addElement(user);
			}
			return result;
		}else
			return null;
	}
	
	// return a list of users, containing the input string
	public Vector<ChatUserInfo> searchUserList(String input){
		if (userList != null){
			if (input == "")
				return getUserList();
			else{
				Vector<ChatUserInfo> result = new Vector<>();
				for (ChatUserInfo user : userList.values() ){
					if (user.getUsername().contains(input))
						result.addElement(user);
				}
				return result;
			}
		}else
			return null;
	}
}
