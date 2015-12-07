package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class WaitingContainer{

	private String fName;
	private String path;
	private InetAddress addr;
	
	public WaitingContainer(String fName, String path, InetAddress addr){
		this.fName = fName;
		this.path = path;
		this.addr = addr;
	}
	
	public String getfName() {
		return fName;
	}

	public String getPath() {
		return path;
	}

	public InetAddress getAddr() {
		return addr;
	}
}

public class TCPServer extends Thread{
	
	int serverPort = 2042;
	private ServerSocket listenSocket;

	private HashMap<InetAddress, Queue<WaitingContainer>> waitingQueue = new HashMap<InetAddress, Queue<WaitingContainer>>();
	
	public TCPServer(){
		try {
			listenSocket = new ServerSocket(serverPort);
			System.out.println("start listening to port " + serverPort);
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void addWaitingRequest(String fName, String path, InetAddress addr){
		if (!waitingQueue.containsKey(addr)){
			waitingQueue.put(addr, new LinkedList<WaitingContainer>());
		}
		waitingQueue.get(addr).add(new WaitingContainer(fName, path, addr));
	}
	
	public void run(){
		while (true){
			try {
				Socket connectedSocket = listenSocket.accept();
				// regarde si l'adresse IP correspond à la réception d'un fichier en attente
				if (waitingQueue.containsKey(connectedSocket.getInetAddress())){
					// récuperer les données de l'autre
					WaitingContainer tmpContainer = waitingQueue.get(connectedSocket.getInetAddress()).poll();
					TCPReceiverBeta tmpReceiver = new TCPReceiverBeta(connectedSocket, tmpContainer.getfName(), tmpContainer.getPath());
				}
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
