package network;

import java.io.*;
import java.net.*;

public class TCPReceiver extends Thread{
	
	private ServerSocket listeningServer; 
	private Socket connectedSocket;
	private BufferedInputStream Sreader; 
	private BufferedOutputStream Swriter;
	private String filePath ;

	public TCPReceiver(int port) throws IOException{
		this.listeningServer = new ServerSocket(port); 
		this.connectedSocket = this.listeningServer.accept();
		this.Sreader=new BufferedInputStream(this.connectedSocket.getInputStream());
		this.Swriter=new BufferedOutputStream(this.connectedSocket.getOutputStream());
		this.start();
	}
	
	public BufferedInputStream getReader(){
		return this.Sreader; 
	}
	
	public BufferedOutputStream getWriter(){
		return this.Swriter; 
	}
	
	public void receivedFile(){
		
		File recFile = new File(this.filePath);
		
		try {
			FileOutputStream fos = new FileOutputStream(recFile);
			while(Sreader.available()!= 0 )
				fos.write(Sreader.read());
			fos.close();
			this.connectedSocket.close();
			FileNI.getInstance().addReceivedBuffer(recFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 public void run(){
		
			 receivedFile();
		 
	 }
}
