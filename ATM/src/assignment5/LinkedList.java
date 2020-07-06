/* Name: Theresa Kahale
   Date Last Modified: 29/03/2020
   Project Description: This project is used to implement class LinkedList 
   Other files: Node.java
 */

package assignment5;

import java.util.Scanner;

public class LinkedList {
	private Node header;
	static Scanner scan = new Scanner(System.in);

	public LinkedList() {
		header = null;
	}
	
	public Node getHeader() {
		return header;
	}

	public void setHeader(Node header) {
		this.header = header;
	}
	
	public void add(Object o) {
		// adds new Node at the end of the LinkedList 
		Node n = new Node(o);
		Node current = header;
		
		if(current == null)  // Empty list
			header = n;
		else {
			while(current.getNext()!= null)
				// move current to get next of current pointing to null (end of list)
				current = current.getNext();
			current.setNext(n);
		}
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
	
	public Node delete(int i) {
		/* 
		 * deletes node @index i, returns a pointer to the deleted node, null if i is an invalid index
		*/
		Node current = header;
		if((header == null) || i<0 || i>size()-1) {
			return null;
		}
		else {
			if(i == 0) {
				// removing 1st node and returning the 1st node
				header = header.getNext();
				current.setNext(null);  // to detach current (1st node)
				return current;
			}
			else {
				for(int j=0;j<i-1;j++) {   // I stop at node i-1
					current = current.getNext();
				}
				Node temp = current.getNext();
				current.setNext(temp.getNext());
				temp.setNext(null);
				return temp;
			}
		}
	}
	
	public boolean insert(Object o, int i) {
		/* inserts o @index i, returns True if i is a valid index, F otherwise*/
		Node node = new Node(o);
		if((header == null) || i<0 || i>size()) {
			return false;
		}
		else {
			if(i==0) {
				// adding an element at the beginning
				node.setNext(header);
				header = node;
				return true;
			}
			else {
				Node current = header;
				for(int j=0; j<i-1;j++) 
					current = current.getNext();
				node.setNext(current.getNext());
				current.setNext(node);
				return true;
			}
		}
	}
	
	public Object get(int i) {
		/* returns Object @index i, null if i is an invalid index*/
		if((header == null) ||i<0 || i>size()-1) {
			return null;
		}
		else {
			Node current = header;
			for(int j=0; j<i;j++) 
				current = current.getNext();
			return current.getInfo();
		}
	}

	public LinkedList append(LinkedList l1, LinkedList l2) {
		/*
		 * appends l2 to l1 and returns a pointer to the amended l1 
		 */
		if(l1==null || l1.getHeader()== null) {
			return l2;
		}
		if(l2==null || l2.getHeader()== null) {
			return l1;
		}
		else {
			Node current = l1.getHeader();
			while(current.getNext()!=null) {
				current = current.getNext();
			}
			current.setNext(l2.getHeader());
			l2 = null;
			return l1;
		}
	}
	
	@Override
	public String toString() {
		Node current = header;
		
		if(current == null) {
			return "LinkedList is empty!";
		}
		String ans = "";
		while (current != null) {
			ans += " " + current.getInfo();
			current = current.getNext();
		}
		
		return ans;
	}
	
	public static int checkInt() {
		// checks for the validity of the integer scanned, keeps asking for a positive number
		boolean is_Nb = false; int i = 0;
		do {
			try {
				i = Integer.parseInt(scan.nextLine());
				is_Nb= true;
				if(i<0) {
					System.out.println("Enter a positive number");
					is_Nb = false;
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}while(!is_Nb);
		return i;
		
	}
	
	public static LinkedList createList() {
		// creates a LinkedList, scans the size of it and each object and returns the final added list
		System.out.println("Enter the size of the LinkedList ");
		int size = checkInt();     // get size of ll
		LinkedList list = new LinkedList();
		String scanned_obj = "";
		for(int i = 0; i<size;i++) {
			System.out.println("Enter Object " + (i+1)+" ");
			scanned_obj = scan.nextLine();
			try {
				// Adding an Integer Object
				list.add(Integer.parseInt(scanned_obj));
			}
			catch(NumberFormatException e) {
				// Adding a Double Object
				try{
					list.add(Double.parseDouble(scanned_obj));
				}
				catch(NumberFormatException e1) {
					// Adding a String object
					list.add(scanned_obj);
				}
			}
		}
		return list;
	}
	
}



