package main;

import java.io.*;

public class ChatFileRequestInfo {

	private ChatUserInfo userInfo;
	private File fileToSend;
	
	public ChatFileRequestInfo(File file, ChatUserInfo info){
		this.userInfo = info;
		this.fileToSend = file;
	}
	
	public File getFile(){
		return fileToSend;
	}
	
	public ChatUserInfo getDestination(){
		return userInfo;
	}
}
