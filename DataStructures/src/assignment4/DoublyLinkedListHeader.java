/* Name: Theresa Kahale
   Date Last Modified: 31/03/2020
   Project Description: This project is used to implement class DoublyLinkedListHeader 
   Other files: DNode.java, HeaderNode.java
 */

package assignment4;

import java.util.*;

public class DoublyLinkedListHeader {

	private HeaderNode header;
	static Scanner scan = new Scanner(System.in);

	public DoublyLinkedListHeader() {
		header = new HeaderNode();
	}

	public HeaderNode getHeader() {
		return header;
	}

	public void setHeader(HeaderNode h) {
		header = h;
	}

	public void add(Object o) {
		// adds new Node to the DLLHeader at the end
		DNode n = new DNode(o);
		header.setCount(header.getCount()+1);

		if(header.getFirst() == null) {  // Empty list
			header.setFirst(n);
			header.setLast(n);
		}
		else {
			DNode current = header.getLast();    // pointing now to the last element
			current.setNext(n);
			n.setPrev(current);
			header.setLast(n);
		}
	}
	
	public int size() {
		return header.getCount();
	}
	
	public Object get(int i) {
		/* returns Object @index i, null if i is an invalid index */
		DNode current = header.getFirst();
		if((current == null) || i<0 || i>size()-1) {
			return null;
		}
		else {
			for(int j=0; j<i;j++) 
				current = current.getNext();
			return current.getInfo();
		}
	}

	public boolean insert(Object i, int j) {
		/* inserts object i @ index j, returns true is inserted correctly, F if j is out of Bounds*/
		DNode current = header.getFirst();

		if(j<0 || j>size()) {
			return false;
		}
		
		DNode n = new DNode(i);
		if (header.getFirst() == null) {  // Empty list
			header.setFirst(n);
			header.setLast(n);
			header.setCount(header.getCount()+1);
			return true;
		}
		if(j==0) {
			// inserting element at the beginning
			current.setPrev(n);
			n.setNext(current);
			header.setFirst(n);
			header.setCount(header.getCount()+1);
			return true;
		}
		else if(j == size()) {
			// inserting element at the end of the list
			header.getLast().setNext(n);
			n.setPrev(header.getLast());
			header.setLast(n);
			header.setCount(header.getCount()+1);
			return true;
		}
		else {
			for(int k=0; k<j-1; k++) 
				current = current.getNext();
			n.setNext(current.getNext());
			n.setPrev(current);
			current.setNext(n);
			n.getNext().setPrev(n);
			header.setCount(header.getCount()+1);
			return true;
		}
		
	}
	
	public DNode delete(int i) {
		/*deletes node @index i, returns a pointer to the deleted node, null if i is an invalid index*/
		DNode current = header.getFirst();        // pointing to 1st node
		if((current == null) || i<0 || i>size()-1) {
			return null;
		}
		else {
			if(i == 0) {
				// removing 1st node and returning the 1st node
				if(size() == 1) {
					header.setFirst(null);
					header.setLast(null);
					header.setCount(size()-1);
					return current;
				}
				else {
					header.setFirst(current.getNext());
					header.getFirst().setPrev(null);
					current.setNext(null);
					header.setCount(size()-1);
					return current;
				}
			}
			else {
				for(int k=0;k<i;k++) 
					current = current.getNext();
				current.getPrev().setNext(current.getNext());
				if(current.getNext()!= null) {
					current.getNext().setPrev(current.getPrev());
					current.setNext(null);
				}
				else if(current.getNext()==null)
					// deleting last node of the list 
					header.setLast(current.getPrev());
				current.setPrev(null);
				header.setCount(size()-1);
				return current;
			}
		}
	}
	
	@Override
	public String toString() {
		
		if(header.getFirst() == null) {
			return "DLLHeader is empty!";
		}
		String ans = "DLLHeader";
		DNode current = header.getFirst();

		while (current != null) {
			ans += " " + current.getInfo();
			current = current.getNext();
		}
		
		return ans;
	}

	public static int checkInt() {
		// checks for the validity of the int scanned, making sure it's a positive non zero number 
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

	public static Integer scanInt() {
		// scan an Integer with no restrictions from the user
		boolean is_Nb = false; int i = 0;
		do {
			try {
				i = Integer.parseInt(scan.nextLine());
				is_Nb= true;
			}
			catch(NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}while(!is_Nb);
		return i;
	}
	
	public static DoublyLinkedListHeader createList() {
		// case 1
		// creates a DoublyLinkedListHeader, scans the size of it and each object and returns the final added list
		System.out.println("Enter the size of the DLLHeader list ");
		int size = checkInt();     // get size of ll
		DoublyLinkedListHeader list = new DoublyLinkedListHeader();
		Integer scanned_obj;
		for(int i = 0; i<size;i++) {
			System.out.println("Enter Object " + (i+1)+" ");
			scanned_obj = checkInt();
			list.add(scanned_obj);
		}
		return list;
	}
	
	public DoublyLinkedListHeader addObject(DoublyLinkedListHeader list) {	
		/*
		 * case 2 
		 * adds scanned object at the scanned index from the user and returns list
		*/
		System.out.println("Enter an index!");
		int index = scanInt();
		System.out.println("Enter the Integer Object!");
		Integer obj = scanInt();
		if(!list.insert(obj, index)) {
			System.out.println("Invalid Index! The list is not modified");
		}
		else {
			System.out.println("The list has been modified!");
		}
		return list;
	}
	
	public DoublyLinkedListHeader deleteObj(DoublyLinkedListHeader list) {
		/*
		 * case 3 
		 * deletes the object at the scanned index from the user and returns modified list
		*/
		System.out.println("Enter an index!");
		int index = scanInt();
		DNode temp= list.delete(index);
		if(temp == null) {
			System.out.println("Invalid Index! The list is not modified");
		}
		else {
			System.out.println("The list has been modified!");
		}
		return list;
	}
	
	public int calculSum(DoublyLinkedListHeader list) {
		// case 5
		// returns sum of the integers stored in the list
		if(list.getHeader().getFirst() == null) {
			return 0;
		}
		else {
			int sum =0;
			DNode current = list.getHeader().getFirst();
			while(current!= null) {
				sum += ((Integer)current.getInfo()).intValue();
				current = current.getNext();
			}
			return sum;
		}
	}
	
	public DoublyLinkedListHeader reverseList(DoublyLinkedListHeader list) {
		// case 4
		// reverses the nodes in list and returns the new list 
		DoublyLinkedListHeader new_list = new DoublyLinkedListHeader();
		for(int j = list.size()-1; j>=0;j--) {
			new_list.add(list.get(j));
		}
		return new_list;
	}
	
	public HeaderNode[] splitList(DoublyLinkedListHeader list) {
		/*
		 * case 6 
		 * asks for an index i and returns array with pointers to two lists. The first list contains the elements between index
		 * 0 and i-1 and the second list contains the rest
		*/
		HeaderNode[] array = new HeaderNode[2];
		System.out.println("Enter an index");
		int index = scanInt();
		if(index<0 || index> list.size()) {
			System.out.println("Cannot Split List! Invalid Index!");
			return null;
		}
		else {
			DoublyLinkedListHeader l1 = new DoublyLinkedListHeader();
			DoublyLinkedListHeader l2 = new DoublyLinkedListHeader();
			for(int j=0; j<index;j++) {
				l1.add(list.get(j));
			}
			for(int j=index; j<list.size();j++) {
				l2.add(list.get(j));
			}
			System.out.println("l1 " + l1);
			System.out.println("l2 " + l2);
			array[0] = l1.getHeader();
			array[1] = l2.getHeader();
			return array;
		}
	}
	
	public static void menu() {
		boolean showMenu = true;
		int count = 0;
		DoublyLinkedListHeader list = new DoublyLinkedListHeader();
		while(showMenu) {
			System.out.println("\n1.	Make list");
			System.out.println("2.	Insert");
			System.out.println("3.	Delete");
			System.out.println("4.	Reverse");
			System.out.println("5.	Add");
			System.out.println("6.	Split");
			System.out.println("7.	Exit");
			System.out.println("- - - - - - - - - - - - - ");
			System.out.println("Input your choice");
			
			int in = checkInt();
			if((count == 0) && (in ==7))
				in =7;
			else if((count ==0) && ((in ==2)|| (in ==3) || (in ==4) || (in ==5) || (in ==6))) {
				in =1;
				System.out.println("First, you have to create your list!");
			}
			switch (in) {
			case 1:
				list = createList();
				System.out.println(list);
				count++;
				continue;
			case 2:		
				list = list.addObject(list);
				System.out.println(list);
				continue;
			case 3:
				list = list.deleteObj(list);
				System.out.println(list);
				continue;
			case 4:
				list = list.reverseList(list);
				System.out.println(list);
				continue;
			case 5:
				int sum = list.calculSum(list);
				System.out.println("Sum of Integers in the list is " + sum);
				continue;
			case 6:
				HeaderNode[] array = list.splitList(list);
				if(array != null) {
					System.out.println("List l1 pointer" + array[0]);
					System.out.println("List l2 pointer" + array[1]);
				}
				continue;
			case 7:
				System.out.println("The program is terminated");
				showMenu = false;	
				break;
			default:
				System.out.println("You entered the wrong input!");
				showMenu = true;	
				break;
			}			
		}
	}

	public static void main(String[] args) {
		menu();
	}
}
