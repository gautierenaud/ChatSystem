package network;

import java.io.*;
import java.net.*;

public class TCPReceiver {
	
	private ServerSocket listeningServer; 
	private Socket connectedSocket;
	private BufferedReader Sreader; 
	private BufferedWriter Swriter;

	public TCPReceiver(int port) throws IOException{
		this.listeningServer = new ServerSocket(port); 
		this.connectedSocket = this.listeningServer.accept();
		this.Sreader=new BufferedReader(new InputStreamReader(this.connectedSocket.getInputStream()));
		this.Swriter=new BufferedWriter(new OutputStreamWriter(this.connectedSocket.getOutputStream()));
	}
	
	public BufferedReader getReader(){
		return this.Sreader; 
	}
	
	public BufferedWriter getWriter(){
		return this.Swriter; 
	}
}
