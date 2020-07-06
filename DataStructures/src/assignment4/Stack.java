package assignment4;

public class Stack {
	// FILO or LIFO Order: add rm beg or add rm end 
	private Node top;
	public Stack() {
		top = null;
	}
	
	public void push (Object o) {
		// Push at the beginning 
		Node n=new Node(o);
		if(top==null)
			top=n;
		else {
			n.setNext(top);
			top=n;
		}
	}
	
	public Object pop() {
		// Pop top element (at beg) on stack and returns null if stack is empty
		if (top==null)
			return null;
		else {
			Node current=top;
			top=top.getNext();
			current.setNext(null);
			return current.getInfo();
		}
	}
	
	public Object peek() {
		/*
		 * returns element at top of Stack, null if empty stack 
		 */
		if(top==null)
			return null;
		else {
			return top.getInfo();
		}
	}
	
	public boolean isEmpty() {
		if (top==null)
			return true;
		else return false;
	}

	public String toString() {
		return "Stack [top=" + top + "]";
	}

	

}
