package assignment4;

public class DLL {
	private DNode header;
	
	public DLL() {
		header = null;
	}
	
	public void add(Object o) {
		// adds new Node to the Doubly LinkedList at the end
		DNode n = new DNode(o);
		DNode current = header;
		
		if(current == null)  // Empty list
			header = n;
		else {
			while(current.getNext()!= null)
				current = current.getNext();
			current.setNext(n);
			n.setPrev(current);
		}
	}
	
	public int size() {
		// returns # nodes on the list
		if(header == null) { // Empty list 
			return 0;
		}
		else {
			int size = 1; // else means at least I have one node
			DNode current = header;
			while(current.getNext()!= null) {
				size++;
				current = current.getNext();
			}
			return size;
		}
	}
	
	public DNode delete(int i) {
		/*
		 * deletes node @index i, returns a pointer to deleted node, null if i invalid
		 */
		if(header == null || i<0 || i> size()-1) {
			return null;
		}
		else {
			DNode current = header;
			if(i ==0) {
				// deleting 1st node
				if(size()==1) {
					header = null;
					return current;
				}
				else {
					// deleting 1st node and the list has more than 1 node
					header = header.getNext();
					current.setNext(null);
					header.setPrev(null);
					return current;
				}
			}
			else {
				// delete node != 1st 
				for(int j=0; j<i; j++)
					current = current.getNext();
				DNode pointer = current; // saving the node to be returned
				current.getPrev().setNext(current.getNext());
				if(current.getNext()!= null) {
					// not deleting the last node
					current.getNext().setPrev(current.getPrev());
					pointer.setNext(null);
				}
				pointer.setPrev(null);
				return pointer;
			}
		}
	}
	
	public boolean insert(Object o, int i) {
		/* inserts o @index i, returns True if i is a valid index, F otherwise */
		if(i<0 || i>size())
			return false;
		else {
			DNode current = header;
			DNode n = new DNode(o);
			if(i==0) {
				if(header == null) {
					header = n;
					return true;
				}
				else { // adding to beginning 
					header.setPrev(n);
					n.setNext(header);
					header = n;
					return true;
				}
			}
			else if (i==size()) {
				// adding at the end of the list 
				for(int k = 0; k<i-1; k++)
					current = current.getNext();   // point to the one before where I want to insert (here to the last)
				n.setPrev(current);
				current.setNext(n);
				return true;
			}
			else{
				for(int k = 0; k<i-1; k++)
					current = current.getNext();   // point to the one before where I want to insert
				n.setNext(current.getNext());
				n.setPrev(current);   // connected n 
				current.setNext(n);   
				n.getNext().setPrev(n);
				return true;
			}
		}
	}
	@Override
	public String toString() {
		DNode current = header;
		
		if(current == null) {
			return "DLL is empty!";
		}
		String ans = "DLL [";
		while (current != null) {
			ans += " " + current.getInfo();
			current = current.getNext();
		}
		
		return ans+"]";
	}


	public static void main(String[] args) {
		DLL dll = new DLL();
		dll.add(3);
		System.out.println(dll);
		dll.delete(0);
		dll.add(4);
		dll.add(5);
		dll.add(67);
		System.out.println(dll);
		System.out.println(dll.size());

	}
	
}
