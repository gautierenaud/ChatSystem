package network;

import java.io.*;
import java.net.*;

public class TCPSender {
	
	private Socket clientSocket;
	private BufferedReader Sreader; 
	private BufferedWriter Swriter;
	private InetAddress distIP;
	
	public TCPSender( int port) throws IOException{
		this.clientSocket = new Socket(this.distIP, port);
		this.Sreader=new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
		this.Swriter=new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream()));
	}

	public BufferedReader getReader(){
		return this.Sreader; 
	}
	
	public BufferedWriter getWriter(){
		return this.Swriter; 
	}
	
	public void setAddress(InetAddress address){
		this.distIP = address; 
	}
	}

