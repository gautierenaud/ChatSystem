package main;

import java.net.*;
import java.util.*;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class ChatUserInfo {
	
	// opponent name we get through messages
	private String username;
	// IP address of the opponent
	private InetAddress address;
	// unique key, made from the username and the ip address
	private String userID;

	public ChatUserInfo(String name, InetAddress ip){
		this.username = name;
		this.address = ip;
		this.userID = name + "@" + ip.toString();
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
