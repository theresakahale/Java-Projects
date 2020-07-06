/* Name: Theresa Kahale
   Date Last Modified: 29/03/2020
   Project Description: This project is used to implement class Node 
   Other files: LinkedList.java
 */

package assignment4;

public class Node {
	private Object info;
	private Node next;
	
	public Node() {
		info = null;
		next = null;
	}

	public Node(Object o) {
		info = o;
		next = null;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object i) {
		info = i;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node n) {
		next = n;
	}

	@Override
	public String toString() {
		return "Node [info=" + info + ", next=" + next + "]";
	}
}
