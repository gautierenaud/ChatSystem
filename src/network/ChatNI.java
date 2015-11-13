package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface; 
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InterfaceAddress; 
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import common.Message;
import main.ChatMediator;
// class that will control all the network-related operations

public class ChatNI {

	private static ChatNI instance;
	private MessageNI messNI; 
	private FileNI fileNI; 
	private Enumeration<NetworkInterface> networkInterfaces; 

	private ChatNI(){
		this.messNI = MessageNI.getInstance();
		messNI.start();
		try {
			this.networkInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/*
		try{
			// for future implementation
			this.fileNI = FileNI.getInstance();
		}catch (IOException e){
			System.err.println(e);
		}*/
	}

	public static ChatNI getInstance(){
		if (instance == null)
			instance = new ChatNI();
		return instance;
	}
	//methode qui ajoute au stack de MessageNI
	public void sendMessage(Message msg, InetAddress addr){
		messNI.addMsgBuff(new MessAddress(msg, addr));
	}
	// TO DO
	/*
	 * addresse boadcast pas en dur
	 * 
	 */
	public ArrayList<InterfaceAddress> getInterfacesAddresses(){
		ArrayList<InterfaceAddress> listaddr = new ArrayList<InterfaceAddress>(); 
		while(networkInterfaces.hasMoreElements())
			listaddr.addAll((ArrayList<InterfaceAddress>) networkInterfaces.nextElement().getInterfaceAddresses());
		return listaddr; 
	}

	public void sendBroadCast(Message msg){// � modifer car broadcast en dur <"!>
		for(InterfaceAddress index : this.getInterfacesAddresses()){
			if (index.getBroadcast() != null)
				this.sendMessage(msg , index.getBroadcast());
		}
	}
	
	//methode qui recupere un message arrive
	public void messageReceived(Message msg , InetAddress addr){
		ChatMediator.getInstance().messageReceived(msg, addr); 
	}
}
 