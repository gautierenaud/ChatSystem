package main;

import java.net.*;

public class ChatUserInfo {
	
	// opponent name we get through messages
	private String username;
	// IP address of the opponent
	private InetAddress address;
	// unique key, made from the username and the ip address
	private String userID;

	private int unreadCount;
	
	public ChatUserInfo(String name, InetAddress ip){
		this.username = name;
		this.address = ip;
		this.userID = name + "@" + ip.toString();
		this.unreadCount = 0;
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
	
	public int getUnreadCount(){
		return unreadCount;
	}
	
	// modify unreadCount
	public void incrementUnreadCount(){
		unreadCount++;
	}
	
	public void resetUnreadCount(){
		unreadCount = 0;
	}
}
