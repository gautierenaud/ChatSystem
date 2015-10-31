package network;

import java.io.IOException;

// class that will control all the network-related operations

public class NetworkInterface {

	private static NetworkInterface instance;
	private MessageNI messNI; 
	private FileNI fileNI; 

	private NetworkInterface() throws IOException {
		this.messNI = MessageNI.getInstance();
		this.fileNI = FileNI.getInstance(); 
	}

	public static NetworkInterface getInstance() throws IOException {
		if (instance == null)
			instance = new NetworkInterface();
		return instance;
	}
	
}
 