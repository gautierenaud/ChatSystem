package network;

import java.net.InetAddress; 
import java.io.File;

public class FileAddr {
	private File file; 
	private InetAddress addr; 

	public FileAddr (File f, InetAddress address){
		this.file = f; 
		this.addr = address; 
	}

	public InetAddress getAddress(){
		return this.addr; 
	}
	
	public File getFile(){
		return this.file; 
	}
}
