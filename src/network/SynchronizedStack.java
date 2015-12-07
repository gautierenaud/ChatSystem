package network;

import java.util.Stack;

public class SynchronizedStack<E>{
	
	private Stack<E> synchronizedStack;
	
	private SynchronizedStack<E> instance;
	public synchronized SynchronizedStack<E> getInstance(){
		if (instance == null)
			this.instance = new SynchronizedStack<>();
		return this.instance;
	}
	
	private SynchronizedStack(){
		synchronizedStack = new Stack<E>();
	}
	
	public void push(E e){
		synchronizedStack.push(e);
	}
	
	public boolean isEmpty(){
		return synchronizedStack.isEmpty();
	}
	
	public E pop(){
		return synchronizedStack.pop();
	}
	
	public void add(E e){
		synchronizedStack.add(e);
	}
}
