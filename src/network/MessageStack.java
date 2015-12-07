package network;

import java.util.Stack;

public class MessageStack {

	private Stack<MessAddress> stack;
	
	private static MessageStack instance;
	private MessageStack(){
		stack = new Stack<>();
	}
	
	public synchronized static MessageStack getInstance(){
		if (instance == null)
			instance = new MessageStack();
		return instance;
	}
	
	public void add(MessAddress messAddress){
		stack.add(messAddress);
	}
	
	public void push(MessAddress messAddress){
		stack.push(messAddress);
	}
	
	public boolean isEmpty(){
		return stack.isEmpty();
	}
	
	public MessAddress pop(){
		return stack.pop();
	}
}
