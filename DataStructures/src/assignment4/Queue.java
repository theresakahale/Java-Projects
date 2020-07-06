package assignment4;

public class Queue {
	// FIFO or LILO Order: here add end remove beg
	private Node header;

	public Queue() {
		header = null;
	}
	
	public void enqueue(Object o) {
		// Add o to the end of the list
		Node n = new Node(o);
		Node current = header;
		if (header == null) { // Empty list
			header = n;
		} else {
			while (current.getNext() != null)
				current = current.getNext();
			current.setNext(n);
		}
	}
	public Object dequeue() {
		/* 
		 * dequeue element and return it, returns null if queue is empty (remove at beginning)
		 */
		if(header==null)
			return null;
		else {
			Node current = header;
			header = current.getNext();
			current.setNext(null);
			return current.getInfo();
		}
	}

	public boolean empty() {
		// return header==null
		if(header==null)
			return true;
		else return false;
	}
	
	public int size() {
		// returns # nodes on the list
		if(header == null) { // Empty list 
			return 0;
		}
		else {
			int size = 1; // else means at least I have one node
			Node current = header;
			while(current.getNext()!= null) {
				size++;
				current = current.getNext();
			}
			return size;
		}
	}
	
}
