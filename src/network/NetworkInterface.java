package network;

import java.io.IOException;

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
	
}
 