package network;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Stack;

public class FileNI extends Thread{

	public static FileNI instance; 
	 
	
	private FileNI() throws IOException{
		this.tcpReceiver = null;// port arbitraire � discuter ! 
		this.tcpSender = new TCPSender( 2042); 
		this.fileToSendBuffer = new Stack<FileAddr>(); 
		this.start();
	}
	
	public static FileNI getInstance() throws IOException{
		if(instance == null)
			instance= new FileNI(); 
		return instance; 
	}
	  
	private Stack<FileAddr> fileToSendBuffer; 
	private Stack<File> fileReceivedBuffer;
	private TCPSender tcpSender;
	private TCPReceiver tcpReceiver;
	
	public void sendFile(FileAddr F){
		this.fileToSendBuffer.push(F);
	}
	
	public void checkSendFile(){
		while(fileToSendBuffer.isEmpty()== false){
			FileAddr fileToSend = this.fileToSendBuffer.pop(); 
			this.tcpSender.setAddress(fileToSend.getAddress());
			try {
				FileInputStream fis = new FileInputStream(fileToSend.getFile());
				while(fis.available()!=0)
					this.tcpSender.getWriter().write(fis.read());
				fis.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/*
	 * cette fonction attends que le socket ne soit plus actif et
	 *  renvoie true lorsque c'est le cas.
	 */
	public boolean prepareToReceive(String fName, String path){
		try {
			this.tcpReceiver = new TCPReceiver(2042,fName, path);
			while(tcpReceiver.isAlive());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true; 
	}
	
	public void addReceivedBuffer(File f){
		this.fileReceivedBuffer.push(f);
	}
	
	//TODO : � completer
	public void checkReceivedFile(){
		while(fileReceivedBuffer.isEmpty()==false){
			File recFile = fileReceivedBuffer.pop();
			recFile.getPath();
			
			
		}
	}
	public void run(){
		while(true){
			this.checkSendFile();
			//this.checkReceivedFile();
		}
	}
}
