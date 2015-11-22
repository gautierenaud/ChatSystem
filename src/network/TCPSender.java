package network;

import java.io.*;
import java.net.*;

public class TCPSender {
	
	private Socket clientSocket;
	private BufferedInputStream Sreader; 
	private BufferedOutputStream Swriter;
	private InetAddress distIP;
	
	public TCPSender( int port) throws IOException{
		this.clientSocket = new Socket(this.distIP, port);
		this.Sreader=new BufferedInputStream(this.clientSocket.getInputStream());
		this.Swriter=new BufferedOutputStream(this.clientSocket.getOutputStream());
	}

	public BufferedInputStream getReader(){
		return this.Sreader; 
	}
	
	public BufferedOutputStream getWriter(){
		return this.Swriter; 
	}
	
	public void setAddress(InetAddress address){
		this.distIP = address; 
	}
	}

