package main;

import java.net.InetAddress;
import java.util.HashMap;
import java.io.File;

public class ChatFileRequestList {
	
	private static ChatFileRequestList instance;

	private ChatFileRequestList() {
	}

	public static ChatFileRequestList getInstance() {
		if (instance == null)
			instance = new ChatFileRequestList();
		return instance;
	}
	
	
	
	// the key will be the concatenation of the userID and the fileName
	private HashMap<String, ChatFileRequestInfo> fileRequestList;


	public void init(){
		eraseRequestList();
		fileRequestList = new HashMap<>();
	}
	
	public void eraseRequestList(){
		fileRequestList = null;
	}
	
	public ChatFileRequestInfo getUser(String key){
		if (fileRequestList != null)
			return fileRequestList.get(key);
		else
			return null;
	}
	
	// look for the element
	public boolean isInside(String key){
		if (fileRequestList != null)
			return fileRequestList.containsKey(key);
		else
			return false;
	}
	
	// add an instance
	public synchronized void addInstance(File file, ChatUserInfo info){
		if (fileRequestList != null){
			String tmpKey = info.getUserID() + file.getName();
			if (!fileRequestList.containsKey(tmpKey)){
				fileRequestList.put(tmpKey, new ChatFileRequestInfo(file, info));
			}
		}
	}
	
	// remove an instance
	public void removeInstance(String key){
		if (fileRequestList != null)
			fileRequestList.remove(key);
	}
	
	public File getAddress(String key){
		if (fileRequestList != null){
			if (fileRequestList.containsKey(key)){
				return fileRequestList.get(key).getFile();
			}
		}

	

		return null;
	}

}
