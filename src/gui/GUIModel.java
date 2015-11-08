package gui;

import java.net.InetAddress;
import java.util.*;

// stores data from the controller

public class GUIModel {

	// singleton pattern for the GUIModel
	
	private ChatGUI controller;
	private static GUIModel instance;
	
	private GUIModel(ChatGUI controller){
		this.controller = controller;
		
		// add junk data for test
		this.AddMessage("rgautier@" + InetAddress.getLoopbackAddress().toString(), new MessageStruct("rgautier", "HelloWorld"));
		this.AddMessage("rgautier@" + InetAddress.getLoopbackAddress().toString(), new MessageStruct("autre", "Hello rgautier"));
		
	}

	public static GUIModel getInstance() {
		if (instance == null)
			instance = new GUIModel(ChatGUI.getInstance());
		return instance;
	}
	
	// version for the initialization
	public static GUIModel getInstance(ChatGUI controller){
		if (instance == null)
			instance = new GUIModel(controller);
		return instance;
	}
	
	
	/* location to store data
	 * 
	 * String for your opponent
	 * 
	 * Vector<MessageStruct> for the conversation
	 * 		MessageStruct contains the name of the source, so that we can differentiate the local user from the opponent
	 * 
	 */
	
	private HashMap<String, Vector<MessageStruct>> conversationList = new HashMap<>();
	
	// get the list of the messages
	public Vector<MessageStruct> getMessages(String opponent){
		if (conversationList.containsKey(opponent))
			return conversationList.get(opponent);
		else
			return null;
	}
	
	// add one new message to the list
	// only one thread can use this method at a time
	public synchronized void AddMessage(String opponentID, MessageStruct message){
		// if the conversation does not exists, create a new one
		if (!conversationList.containsKey(opponentID)){
			// create a new conversation list
			conversationList.put(opponentID, new Vector<MessageStruct>());
		}
		// append message at the end of the list
		conversationList.get(opponentID).add(message);
	}
	
	// observer pattern for the messages

}
