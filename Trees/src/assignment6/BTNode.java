/* Name: Theresa Kahale
   Date Last Modified: 5/06/2020
   Project Description: This project is used to design and implement a binary search tree that simulates a dictionary. 
						This tree is used to store definitions of words. Each definition has two parts, a word and its 
						description.  
   Other files: BST.java, Dictionary.java
 */

package assignment6;

public class BTNode {
	private String word;
	private String description;
	private BTNode left;
	private BTNode right;
	
	public BTNode(String w, String d) {
		word = w;
		description = d;
		left = right = null;
	}

	public boolean isLeaf() {
		return (right==null && left == null);
	}
	
	public Object getLeftMostData() {
		if(left == null)
			return "Word: "+ getWord() +"Description: "+ getDescription() ;
		else
			return left.getLeftMostData();
	}
	
	public void inOrderPrint() {
		if(left ==null) {
			System.out.println(toString()+"\n");
			
		}
		else if(left!=null) {
			left.inOrderPrint();
		}
	}

	public String getWord() {
		return word;
	}

	public void setWord(String w) {
		word = w;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String d) {
		description = d;
	}

	public BTNode getLeft() {
		return left;
	}

	public void setLeft(BTNode l) {
		left = l;
	}

	public BTNode getRight() {
		return right;
	}

	public void setRight(BTNode r) {
		right = r;
	}

	@Override
	public String toString() {
		return "Word= " + word + ", Description=" + description;
	}
	
}
