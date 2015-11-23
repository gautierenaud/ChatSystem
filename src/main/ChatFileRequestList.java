package main;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Vector;

public class ChatFileRequestList {
	
	private static ChatFileRequestList instance;

	private ChatFileRequestList() {
	}

	public static ChatFileRequestList getInstance() {
		if (instance == null)
			instance = new ChatFileRequestList();
		return instance;
	}
	
	// HashMap for storing user's information
		private HashMap<String, ChatFileRequest> fileRequestList;

		public void init(){
			fileRequestList = new HashMap<>();
		}
		
		public void eraseUserList(){
			fileRequestList = null;
		}
		
		public ChatUserInfo getUser(String userID){
			if (fileRequestList != null)
				return fileRequestList.get(userID);
			else
				return null;
		}
		
		// look for the element
		public boolean isInside(String userID){
			if (fileRequestList != null)
				return fileRequestList.containsKey(userID);
			else
				return false;
		}
		
		// add an instance
		public synchronized void addInstance(String username, InetAddress address){
			if (fileRequestList != null){
				String tmpID = username + "@" + address.toString();
				if (!fileRequestList.containsKey(tmpID)){
					fileRequestList.put(tmpID, new ChatUserInfo(username, address));
				}
			}
		}
		
		// remove an instance
		public void removeInstance(String userID){
			if (fileRequestList != null)
				fileRequestList.remove(userID);
		}
		
		public InetAddress getAddress(String userID){
			if (fileRequestList != null){
				if (fileRequestList.containsKey(userID)){
					return fileRequestList.get(userID).getAddress();
				}
			}
			return null;
		}
		
		// return a list of the users
		public Vector<ChatUserInfo> getUserList(){
			if (fileRequestList != null){
				Vector<ChatUserInfo> result = new Vector<>();
				for (ChatUserInfo user : fileRequestList.values() ){
					result.addElement(user);
				}
				return result;
			}else
				return null;
		}
		
		// return a list of users, containing the input string
		public Vector<ChatUserInfo> searchUserList(String input){
			if (fileRequestList != null){
				if (input == "")
					return getUserList();
				else{
					Vector<ChatUserInfo> result = new Vector<>();
					for (ChatUserInfo user : fileRequestList.values() ){
						if (user.getUsername().contains(input))
							result.addElement(user);
					}
					return result;
				}
			}else
				return null;
		}
	
}
