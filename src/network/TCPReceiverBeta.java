package network;

import java.io.*;
import java.net.*;

public class TCPReceiverBeta extends Thread{
	
	private ServerSocket listeningServer; 
	private Socket connectedSocket;
	private BufferedInputStream Sreader; 
	private BufferedOutputStream Swriter;
	private String filePath ;
	private String fileName;
	
	public TCPReceiverBeta(Socket connectedSocket, String fName, String path) throws IOException{
		this.filePath = path; 
		this.fileName = fName;
		this.connectedSocket = connectedSocket;
		this.start();
	}
	
	public BufferedInputStream getReader(){
		return this.Sreader; 
	}
	
	public BufferedOutputStream getWriter(){
		return this.Swriter; 
	}
	
	//cr�e un non listening socket sp�cifique et le referme une fois la connexion termin�
	public void receivedFile(){
		try {
			this.Sreader=new BufferedInputStream(this.connectedSocket.getInputStream());
			this.Swriter=new BufferedOutputStream(this.connectedSocket.getOutputStream());
			File recFile = new File(filePath + "/" +fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int r= 0; 
			
			FileOutputStream fos = new FileOutputStream(recFile);
			r = Sreader.read();
			while(r != -1 ){
				baos.write(r);
				r = Sreader.read();
			}
			baos.writeTo(fos);
			fos.close();
			baos.flush();
			baos.close();
			
			this.connectedSocket.close();
			System.out.println("file received");
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
