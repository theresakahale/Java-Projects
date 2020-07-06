/* Name: Theresa Kahale
   Date Last Modified: 29/03/2020
   Project Description: This project is used to implement class DNode 
   Other files: DoublyLinkedListHeader.java, HeaderNode.java
 */

package assignment4;

public class DNode {
	private Object info;
	private DNode next;
	private DNode prev;

	public DNode() {
		info = null;
		next = null;
		prev = null;
	}

	public DNode(Object o) {
		info = o;
		next = null;
		prev = null;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object i) {
		info = i;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode n) {
		next = n;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode pr) {
		prev = pr;
	}

	@Override
	public String toString() {
		return ""+ info;
	}

}
