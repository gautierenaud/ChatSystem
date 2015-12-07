package main;

import java.util.HashMap;
import java.io.File;

class ChatFileRequestInfo {

	private ChatUserInfo userInfo;
	private File fileToSend;
	
	public ChatFileRequestInfo(File file, ChatUserInfo info){
		this.userInfo = info;
		this.fileToSend = file;
	}
	
	public File getFile(){
		return fileToSend;
	}
	
	public ChatUserInfo getUserInfo(){
		return userInfo;
	}
}

public class ChatFileRequestFromUser {
	
	public ChatFileRequestFromUser() {
		fileRequestList = new HashMap<>();
	}
	
	// the key will be the concatenation of the userID and the fileName
	private HashMap<String, ChatFileRequestInfo> fileRequestList;
	
	public void eraseRequestList(){
		fileRequestList.clear();
	}
	
	public ChatFileRequestInfo getUser(String key){
		return fileRequestList.get(key);
	}
	
	// look for the element
	public boolean isInside(String key){
		return fileRequestList.containsKey(key);
	}
	
	// add an instance
	public synchronized void addInstance(File file, ChatUserInfo info){
		String tmpKey = info.getUserID() + file.getName();
		if (!fileRequestList.containsKey(tmpKey)){
			fileRequestList.put(tmpKey, new ChatFileRequestInfo(file, info));
		}
	}
	
	// remove an instance
	public void removeInstance(String key){
		fileRequestList.remove(key);
	}
	
	public File getFile(String key){
		if (fileRequestList.containsKey(key))
			return fileRequestList.get(key).getFile();
		else
			return null;
	}
	
	public ChatUserInfo getUserInfo(String key){
		if (fileRequestList.containsKey(key))
			return fileRequestList.get(key).getUserInfo();
		else
			return null;
	}
	
	public ChatFileRequestInfo getRequestInfo(String key){
		return fileRequestList.get(key);
	}
	
	public boolean containsRequest(String key){
		return fileRequestList.containsKey(key);
	}
}
