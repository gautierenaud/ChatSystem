package network;

import java.io.IOException;
import java.io.File;
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
	private ArrayList<InterfaceAddress> addressList;

	private ChatNI(){
		this.messNI = MessageNI.getInstance();
		messNI.start();
		try {
			this.networkInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addressList = this.getInterfacesAddresses();
	}

	public static ChatNI getInstance(){
		if (instance == null)
			instance = new ChatNI();
		return instance;
	}
	
	private ChatMediator mediator;
	public void initAll(ChatMediator mediator){
		this.mediator = mediator;
	}
	
	//*********************************SENDING METHODS**************************************************************
	
	//this method pack a Message and an IP into a bigger object, and push it into the sending buffer of MessageNI
	public void sendMessage(Message msg, InetAddress addr){
		messNI.addMsgBuff(new MessAddress(msg, addr));
	}
	
	public void sendFile(File file , InetAddress addr){
		fileNI.sendFile(new FileAddr(file,addr));
	}
	public boolean prepareToReceive(String fName, String path){
		return fileNI.prepareToReceive(fName, path); 
	}
	public void fileReceived(File recFile){
		mediator.fileReceived(recFile);
	}
	
	public ArrayList<InetAddress> getLocalAddresses(){
		ArrayList<InetAddress> result = new ArrayList() ; 
		for(InterfaceAddress index : this.addressList){
			if(index!= null){
				result.add(index.getAddress());
			}
		}
		return result; 
	}
	
	
	public ArrayList<InterfaceAddress> getInterfacesAddresses(){
		ArrayList<InterfaceAddress> listaddr = new ArrayList<InterfaceAddress>(); 
		while(networkInterfaces.hasMoreElements())
			listaddr.addAll((ArrayList<InterfaceAddress>) networkInterfaces.nextElement().getInterfaceAddresses());
		return listaddr; 
	}

	public void sendBroadCast(Message msg){// � modifer car broadcast en dur <"!>
		for(InterfaceAddress index : addressList){
			if (index.getBroadcast() != null){
				this.sendMessage(msg , index.getBroadcast());
			}
		}
	}
	
	//methode qui recupere un message arrive
	public void messageReceived(Message msg , InetAddress addr){
		mediator.messageReceived(msg, addr); 
	}
	
	public void clearAll(){
		
	}
}
 