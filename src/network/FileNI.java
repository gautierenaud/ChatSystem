package network;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Stack;

public class FileNI extends Thread{

	private TCPServer listeningServer;
	
	public static FileNI instance; 
	
	private FileNI() throws IOException{ 
		this.fileToSendBuffer = new Stack<FileAddr>(); 
		this.start();
		
		// start the listening server
		listeningServer = new TCPServer();
	}
	
	public static FileNI getInstance() throws IOException{
		if(instance == null)
			instance= new FileNI(); 
		return instance; 
	}
	  
	private Stack<FileAddr> fileToSendBuffer; 
	private Stack<File> fileReceivedBuffer;
	
	public void sendFile(FileAddr F){
		this.fileToSendBuffer.push(F);
		synchronized (this) {
			notify();
		}
	}
	
	public synchronized void checkSendFile(){		
		while(fileToSendBuffer.isEmpty()== false){
			FileAddr fileToSend = this.fileToSendBuffer.pop();
			try {
				TCPSender tcpSender = new TCPSender(2042, fileToSend.getAddress(), fileToSend);
			} catch (IOException e) {
				e.printStackTrace();
				
				// Maybe send a notification to the user to told him that the transfer has failed
			}
		}
	}
	
	/*
	 * cette fonction attends que le socket ne soit plus actif et
	 *  renvoie true lorsque c'est le cas.
	 */
	public boolean prepareToReceive(String fName, String path, InetAddress addr){
		listeningServer.addWaitingRequest(fName, path, addr);
		return true; 
	}
	
	public void addReceivedBuffer(File f){
		this.fileReceivedBuffer.push(f);
	}
	
	public void checkReceivedFile(){
		while(fileReceivedBuffer.isEmpty()==false){
			File recFile = fileReceivedBuffer.pop();
			recFile.getPath();
		}
	}
	
	public void run(){
		while (true){
			synchronized (this) {
				try {
					wait();
					this.checkSendFile();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
