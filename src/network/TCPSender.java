package network;

import java.io.*;
import java.net.*;

public class TCPSender extends Thread{
	
	private Socket clientSocket; 
	private BufferedOutputStream sWriter;
	private FileAddr fileToSend;
	private ByteArrayOutputStream baos;
	
	public TCPSender( int port, InetAddress addr, FileAddr fileToSend) throws IOException{
		this.clientSocket = new Socket(addr, port);
		this.sWriter = new BufferedOutputStream(this.clientSocket.getOutputStream());
		this.fileToSend = fileToSend;
		
		baos = new ByteArrayOutputStream();
		
		this.start();
	}
	
	public void run(){
		try{
			FileInputStream fis = new FileInputStream(fileToSend.getFile());
			while(fis.available()!=0)
				baos.write(fis.read());
			fis.close();
			baos.writeTo(sWriter);
			sWriter.flush();
			baos.flush();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{

			this.closeSocket();
		}
	}

	public BufferedOutputStream getWriter(){
		return this.sWriter; 
	}
	
	public void closeSocket(){
		try {
			this.clientSocket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
