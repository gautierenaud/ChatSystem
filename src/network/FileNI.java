package network;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Stack;

public class FileNI {

	public static FileNI instance; 
	 
	
	private FileNI() throws IOException{
		this.tcpReceiver = new TCPReceiver(2043);// port arbitraire à discuter ! 
		this.tcpSender = new TCPSender( 2043); 
		this.fileBuffer = new Stack<File>(); 
	}
	
	public static FileNI getInstance() throws IOException{
		if(instance == null)
			instance= new FileNI(); 
		return instance; 
	}
	
	private Stack<File> fileBuffer; 
	private TCPSender tcpSender;
	private TCPReceiver tcpReceiver;
	
}
