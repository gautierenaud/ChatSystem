package main;

import java.util.HashMap;
import java.io.File;

public class ChatFileRequestList {
	
	private static ChatFileRequestList instance;

	private ChatFileRequestList() {
		fileRequestList = new HashMap<>();
	}

	public static ChatFileRequestList getInstance() {
		if (instance == null)
			instance = new ChatFileRequestList();
		return instance;
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
}
