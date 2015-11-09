package network;

import java.io.IOException;
import java.net.InetAddress;
import common.Message;
// class that will control all the network-related operations

public class NetworkInterface {

	private static NetworkInterface instance;
	private MessageNI messNI; 
	private FileNI fileNI; 

	private NetworkInterface(){
		this.messNI = MessageNI.getInstance();
		try{
			this.fileNI = FileNI.getInstance();
		}catch (IOException e){
			System.err.println(e);
		} 
	}

	public static NetworkInterface getInstance(){
		if (instance == null)
			instance = new NetworkInterface();
		return instance;
	}
	//methode qui ajoute au stack et envoie en suivant
	public void sendMessage(Message mess, InetAddress address){//a optimiser car Stack ne sert a rien -->threads pour MessNI?
		messNI.addMsgBuff(mess);
		messNI.sendPacket(address);
	}
	//methode qui recupere un message arriv�
	public Message getMessage(){
		return messNI.turnPacketToMessage();
	}
}
 