package main;

import java.io.*;

public class ChatFileRequestInfo {

	private String destinationID;
	private File fileToSend;
	
	public ChatFileRequestInfo(File file, String destinationID){
		this.destinationID = destinationID;
		this.fileToSend = file;
	}
	
	public File getFile(){
		return fileToSend;
	}
	
	public String getDestination(){
		return destinationID;
	}
}
