package network;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
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
	}
	
	public void checkSendFile(){
		while(fileToSendBuffer.isEmpty()== false){
			FileAddr fileToSend = this.fileToSendBuffer.pop(); 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				TCPSender tcpSender = new TCPSender(2042,fileToSend.getAddress());
				FileInputStream fis = new FileInputStream(fileToSend.getFile());
				while(fis.available()!=0)
					baos.write(fis.read());//this.tcpSender.getWriter().write(fis.read());
				fis.close();
				baos.writeTo(tcpSender.getWriter());
				baos.flush();
				baos.close();
				tcpSender.closeSocket();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/*
	 * cette fonction attends que le socket ne soit plus actif et
	 *  renvoie true lorsque c'est le cas.
	 */
	public boolean prepareToReceive(String fName, String path, InetAddress addr){
		listeningServer.addWaitingRequest(fName, path, addr);
		/*
		try {
			TCPReceiver tcpReceiver = new TCPReceiver(2042,fName, path);
			//while(tcpReceiver.isAlive());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
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
