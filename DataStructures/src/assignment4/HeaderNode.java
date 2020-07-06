/* Name: Theresa Kahale
   Date Last Modified: 29/03/2020
   Project Description: This project is used to implement class HeaderNode 
   Other files: DoublyLinkedListHeader.java, DNode.java
 */

package assignment4;

public class HeaderNode {
	private int count;
	private DNode first;
	private DNode last;
	
	public HeaderNode() {
		count = 0;
		first = null;
		last = null;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int c) {
		count = c;
	}

	public DNode getFirst() {
		return first;
	}

	public void setFirst(DNode f) {
		first = f;
	}

	public DNode getLast() {
		return last;
	}

	public void setLast(DNode l) {
		last = l;
	}

	@Override
	public String toString() {
		return "HeaderNode [count=" + count + ", first=" + first + ", last=" + last + "]";
	}
	
}
