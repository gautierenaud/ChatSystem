package network;

import java.net.DatagramPacket;
import java.util.Stack;

public class PacketStack {
	
	private Stack<DatagramPacket> stack;
	
	private static PacketStack instance;
	private PacketStack(){
		stack = new Stack<>();
	}
	
	public synchronized static PacketStack getInstance(){
		if (instance == null)
			instance = new PacketStack();
		return instance;
	}
	
	public void add(DatagramPacket packet){
		stack.add(packet);
	}
	
	public void push(DatagramPacket packet){
		stack.push(packet);
	}
	
	public boolean isEmpty(){
		return stack.isEmpty();
	}
	
	public DatagramPacket pop(){
		return stack.pop();
	}
}
