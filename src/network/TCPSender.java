package network;

import java.io.*;
import java.net.*;

public class TCPSender {
	
	private Socket clientSocket;
	private BufferedInputStream Sreader; 
	private BufferedOutputStream Swriter;
	
	
	public TCPSender( int port, InetAddress addr) throws IOException{
		this.clientSocket = new Socket(addr, port);
		this.Sreader=new BufferedInputStream(this.clientSocket.getInputStream());
		this.Swriter=new BufferedOutputStream(this.clientSocket.getOutputStream());
	}

	public BufferedInputStream getReader(){
		return this.Sreader; 
	}
	
	public BufferedOutputStream getWriter(){
		return this.Swriter; 
	}
	
	public void closeSocket(){
		try {
			this.clientSocket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
}
