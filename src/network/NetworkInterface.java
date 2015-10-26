package network;

// class that will control all the network-related operations

public class NetworkInterface {

	private static NetworkInterface instance;

	private NetworkInterface() {
	}

	public static NetworkInterface getInstance() {
		if (instance == null)
			instance = new NetworkInterface();
		return instance;
	}
	
}
