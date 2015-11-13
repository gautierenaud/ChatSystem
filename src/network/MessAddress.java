package network;

import java.net.InetAddress;

import common.Message;

// cette classe sert à préparer lamise en paquet du message par MessageNI

public class MessAddress {
	
	private Message mess;
	private InetAddress address; 
	
	public MessAddress (){
		this.mess =null;
		this.address= null;
	}
	
	public MessAddress (Message mess, InetAddress addr){
		this.mess = mess;
		this.address= addr; 
	}

	public Message getMessage(){
		return this.mess; 
	}
	public InetAddress getAddress(){
		return this.address; 
	}
	public void setAdress(InetAddress addr){
		this.address = addr; 
	}
	public void setMessage(Message msg){
		this.mess =msg; 
	}
}
