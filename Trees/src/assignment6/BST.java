/* Name: Theresa Kahale
   Date Last Modified: 5/06/2020
   Project Description: This project is used to design and implement a binary search tree that simulates a dictionary. 
						This tree is used to store definitions of words. Each definition has two parts, a word and its 
						description.  
   Other files: BTNode.java, Dictionary.java
 */

package assignment6;


public class BST {
	private BTNode root;
	public BST() {
		root = null;
	}

	public void add(BTNode r, BTNode node) {
		if (r ==null) {
			root = node;
			System.out.println("Definition successfully added!");
		}
		else if(r.getWord().equalsIgnoreCase(node.getWord()) && r.getDescription().equalsIgnoreCase(node.getDescription())){
			System.out.println("This definition already exists!");
			return;
		}
		else if(r.getWord().compareToIgnoreCase(node.getWord())>=0) {
			if(r.getLeft() == null) {
				r.setLeft(node);
			}
			else 
				add(r.getLeft(), node);
		}
		else {
			if(r.getRight() == null) {
				r.setRight(node);
			}
			else 
				add(r.getRight(), node);
		}
	}
	
	public String listDef(BTNode r, String word, int index) {
		// list definition + index of each 
		String def = "";
		if(r == null)
			return def;
		else if(r.getWord().equalsIgnoreCase(word)) {
			def += def +"\n"+index +". Word: "+ word +"          Description: "+r.getDescription();
			return def + listDef(r.getLeft(), word, index+1);
		}
		else if ((r.getWord()).compareToIgnoreCase(word)>0)
			return listDef(r.getLeft(), word, index);
		else 
			return listDef(r.getRight(), word, index);
	}
	
	public int getSize(BTNode r) {
		if(r == null) {
			return 0;
		}
		else {
			return getSize(r.getLeft())+ getSize(r.getRight()) +1;
		}
	}

	public String[] returnInfo() {
		String[] temp = preOrderPrint(root).split(",");
		return temp;
	}
	

	
	public String findDef(BTNode r, String word) {
		// print all definitions
		String def = "";
		if(r == null)
			return def;
		else if(r.getWord().equalsIgnoreCase(word)) {
			def += def + "\nWord: "+ word +"          Description: "+r.getDescription();
			return def + findDef(r.getLeft(), word);
		}
		else if ((r.getWord()).compareToIgnoreCase(word)>0)
			return findDef(r.getLeft(), word);
		else 
			return findDef(r.getRight(), word);
	}
	
	public void inOrderPrint(BTNode n) {
		if(n == null) {
			System.out.println("Empty Tree");
			return;
		}
		if(n.getLeft()!= null) 
			inOrderPrint(n.getLeft());
		
		System.out.println(n);
		
		if(n.getRight()!= null) 
			inOrderPrint(n.getRight());
	}
	
	public String preOrderPrint(BTNode n) {
		if(n == null) {
			System.out.println("Empty Tree");
			return "";
		}
		String ans = "";
		ans+= n.getWord()+","+ n.getDescription()+",";
		if(n.getLeft()!= null) 
			ans += preOrderPrint(n.getLeft());
		
		if(n.getRight()!= null) 
			ans += preOrderPrint(n.getRight());

		return ans;
	}
	
	public BTNode getRoot() {
		return root;
	}

	public void setRoot(BTNode r) {
		root = r;
	}

	
	@Override
	public String toString() {
		return "BST [root=" + root + "]";
	}
}
