package main;

import java.util.Stack;

/*
 * class to handle all the requests from the outside
 */


class FileRequest{
	private String fileName;
	private float fileSize;
	private String sourceID;
	
	public FileRequest(String fileName, float fileSize, String sourceID){
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.sourceID = sourceID;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public float getFileSize() {
		return fileSize;
	}

	public String getSourceID() {
		return sourceID;
	}
}

public class ChatFileRequestFromOther extends Thread{
	
	// List of the different requests
	private Stack<FileRequest> requestStack;
	
	public ChatFileRequestFromOther() {
		requestStack = new Stack<>();
		this.start();
	}
	
	@Override
	public void run(){
		while (true){
			synchronized (this) {
				try {
					wait();

					if (!requestStack.isEmpty()){
						FileRequest tmp = requestStack.pop();
						ChatMediator.getInstance().fileRequestQuery(tmp.getFileName(), tmp.getFileSize(), tmp.getSourceID());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addRequest(String fileName, float fileSize, String sourceID){
		requestStack.add(new FileRequest(fileName, fileSize, sourceID));
		synchronized (this) {
			notify();
		}
	}
	
	public void clearAll(){
		requestStack.clear();
	}
}
