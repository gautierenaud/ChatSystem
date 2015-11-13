package network;

import java.io.IOException;
import java.net.InetAddress;
import common.Message;
import main.ChatMediator;
// class that will control all the network-related operations

public class ChatNI {

	private static ChatNI instance;
	private MessageNI messNI; 
	private FileNI fileNI; 

	private ChatNI(){
		this.messNI = MessageNI.getInstance();
		try{
			this.fileNI = FileNI.getInstance();
		}catch (IOException e){
			System.err.println(e);
		} 
	}

	public static ChatNI getInstance(){
		if (instance == null)
			instance = new ChatNI();
		return instance;
	}
	//methode qui ajoute au stack de MessageNI
	public void sendMessage(MessAddress msgAddr){
		messNI.addMsgBuff(msgAddr); 
		
	}
	// TO DO
	/*
	 * Ajouter une fonction SendMessage(Message msg) pour broadcast
	 */
	/*public void sendHello(Message msg){
		InetAddress.getAllByName()
	}*/
	//methode qui recupere un message arrivé
	
	public void messageReceived(Message msg , InetAddress addr){
		ChatMediator.getInstance().messageReceived(msg, addr); 
	}
}
 