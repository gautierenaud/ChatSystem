package main;

import java.net.*;
import java.util.*;

public class ChatUserInfo {
	
	// opponent name we get through messages
	private String username;
	// IP address of the opponent
	private InetAddress address;
	// unique key, made from the username and the ip address
	private String userID;
	
	// HashMap for storing user's information
	private HashMap<String, ChatUserInfo> userList = new HashMap<>();
	private ChatUserInfo(String name, InetAddress ip){
		this.username = name;
		this.address = ip;
		this.userID = name + "@" + ip.toString();
	}
	
	public ChatUserInfo getInstance(String userID){
		return userList.get(userID);
	}
	
	// add an instance
	public void addInstance(String username, InetAddress address){
		String tmpID = username + "@" + address.toString();		
		if (!userList.containsKey(tmpID)){
			userList.put(tmpID, new ChatUserInfo(username, address));
		}
	}
	
	// remove an instance
	public void removeInstance(String userID){
		userList.remove(userID);
	}
	
	
	// getters
	
	public String getUsername() {
		return username;
	}
	
	public InetAddress getAddress() {
		return address;
	}
	
	public String getUserID() {
		return userID;
	}	
}
