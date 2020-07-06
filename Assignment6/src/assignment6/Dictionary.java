/* Name: Theresa Kahale
   Date Last Modified: 5/06/2020
   Project Description: This project is used to design and implement a binary search tree that simulates a dictionary. 
						This tree is used to store definitions of words. Each definition has two parts, a word and its 
						description.  
   Other files: BST.java, BTNode.java
 */

package assignment6;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Dictionary {
	public static Scanner scan = new Scanner(System.in);
	private BST dict;
	private static int count = 0;
	private static ArrayList<BTNode> one_level;
	private static ArrayList<BTNode> next_level;
	private static ArrayList<Object> levels ;
	private static String name_file ="";
	
	public Dictionary() {
		dict = new BST();
		one_level = new ArrayList<BTNode>();
		levels = new ArrayList<Object>();
		next_level = new ArrayList<BTNode>();
	}
	
	public BST getDict() {
		return dict;
	}

	public void setDict(BST d) {
		dict = d;
	}

	public static int checkInt() {
		// checks for the validity of the integer scanned, keeps asking for a positive number
		boolean is_Nb = false; int i = 0;
		do {
			try {
				i = Integer.parseInt(scan.nextLine());
				is_Nb= true;
				if(i<=0 || i>=8) {
					System.out.println("Enter a valid number between 1 and 7");
					is_Nb = false;
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}while(!is_Nb);
		return i;
	}
	
	public static FileReader getNameFile(int c) {
		name_file = "";
		FileReader fr = null;
		if(c<3) {
			name_file = scan.nextLine();
			try {
				fr = new FileReader(new File(name_file));
			} catch (FileNotFoundException e) {
				if(c <2) {
					System.out.println("You have "+(2-c) +" more trials.");
					System.out.println("Enter an existing name file ");
				}
				fr = null;
				name_file = "";
				return getNameFile(c+1);
			}
		}
		return fr;
	}
	
	public static String parseFile(BufferedReader br, String str) {
		String def = "";
		String[] info;
		try {
			if((str = br.readLine())!=null) {
				info = str.split(": ");
				if(info.length ==2) {
					def += info[0]+","+info[1]+",";
				}
				return def + parseFile(br, str);
			}
		} catch (IOException e) {
			System.out.println("File is empty");
		}
		return def;
	}
	
	public static Dictionary addDef(Dictionary d, String[] info, int index) {
		if(index <info.length) {
			BTNode node = new BTNode(info[index], info[index+1]);
			d.getDict().add(d.getDict().getRoot(), node);
			addDef(d, info, index+2);
		}
		return d;
	}
	
	public Dictionary createDictionary(Dictionary d) {
		System.out.println("Enter the name of the text file where word definitions are stored: ");
		FileReader fr = getNameFile(count);
		if(fr == null) 
			return d;
		BufferedReader br = new BufferedReader(fr);
		String str = null;
		String[] info = parseFile(br, str).split(",");
		count =0;
		d.getDict().setRoot(null);;
		d = addDef(d, info, count);
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Dictionary has been successfully created!");
		return d;
	}
	
	public static void modifyFile(PrintWriter pw, String[] info, int index) {
		// saves the database
		if(index<info.length) {
			pw.print(info[index]+": "+ info[index+1]+"\n");
			modifyFile(pw,info,index+2);
		}
	}
	
	public void saveFile() {
		if(dict.getRoot() == null) {
			System.out.println("Dictionary is updated and is empty!");
			return;
		}
		String[] info = dict.preOrderPrint(dict.getRoot()).split(",");
		count = 0;
		try {
			PrintWriter pw = new PrintWriter(new File(name_file));
			modifyFile(pw, info, count);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		System.out.println("All Definitions are saved in "+name_file);
	}
	
	public void addDefinition() {
		if(dict.getRoot()==null) {
			System.out.println("Kindly create the dictionary before!");
			return;
		}
		System.out.println("Enter the word you wish to add");
		String word = scan.nextLine();
		System.out.println("Enter its description");
		String description = scan.nextLine();
		BTNode node = new BTNode(word, description);
		dict.add(dict.getRoot(), node);
		System.out.println("Definition successfully added!");
	}
	
	public void remove(String[] t, String word, int index, int choice, int increment) {
		// index to insert // choice to which case if all or one specific // increment to add
		if (choice == 0) {
			// deleting all notes with word (also if only one appearance)
			if(index <t.length) {
				if(t[index].equalsIgnoreCase(word)) {
					t[index] = t[index+1] = null;
					remove(t, word, index+2, choice, increment);
				}
				else {
					BTNode node = new BTNode(t[index], t[index+1]);
					dict.add(dict.getRoot(), node);
					remove(t, word, index+2, choice, increment);
				}
			}
		}
		else {
			if(index <t.length) {
				if(t[index].equalsIgnoreCase(word)) {
					if(choice == increment) {
						// got the node I want to delete 
						t[index] = t[index+1] = null;
						remove(t, word, index+2, choice, increment+1);
					}
					else {
						BTNode node = new BTNode(t[index], t[index+1]);
						dict.add(dict.getRoot(), node);
						remove(t, word, index+2, choice, increment+1);
					}
				}
				else {
					BTNode node = new BTNode(t[index], t[index+1]);
					dict.add(dict.getRoot(), node);
					remove(t, word, index+2, choice, increment);
				}
			}
		}
	}
	
	public void removeDefinition() {
		if(dict.getRoot()==null) {
			System.out.println("Kindly create the dictionary before!");
			return;
		}
		System.out.println("Enter the word you wish to remove from the dictionary");
		String word = scan.nextLine();
		String ans = dict.listDef(dict.getRoot(), word, 1);
		if(ans.equals("")) {
			System.out.println("No definition is found for this word!");
			return;
		}
		String[] info = ans.split("\n");
		String[] temp = dict.returnInfo();
		System.out.println("Kindly find below all the definitions of the word \""+ word+"\"");
		System.out.println(ans);
		count = 0;
		int index_chosen = chooseIndex(info.length-1);
		//System.out.println(index_chosen);
		dict.setRoot(null);
		if(index_chosen ==0)
			remove(temp, word, 0, 0, 0);
		else
			remove(temp, word, 0, index_chosen, 1);
	}
	
	public void searchDef() {
		if(dict.getRoot()==null) {
			System.out.println("Kindly create the dictionary before!");
			return;
		}
		System.out.println("Enter the word you wish to search for its definitions");
		String word = scan.nextLine();
		String ans = dict.findDef(dict.getRoot(), word);
		if(ans.equals("")) 
			System.out.println("The word is not found in the dictionary");
		else {
			System.out.println("The definitions of the word "+ word +" are found below");
			System.out.println(ans);
		}
	}
	
	public static int chooseIndex(int length) {
		int index_chosen = -99;
		String in = "";
		System.out.println("Choose the index of the definition or (a) if you want to delete them all");
		try {
			in = scan.nextLine();
			index_chosen = Integer.parseInt(in);
			if(index_chosen <=0 || index_chosen > length) {
				return chooseIndex(length);
			}
		} catch (NumberFormatException e) {
			if(in.equalsIgnoreCase("a")) {
				index_chosen = 0;
				return index_chosen;
			}
			return chooseIndex(length);
		
		}
		return index_chosen;
	}
	
	public static void getNodes(BTNode r) {
		// get children of a specific node -- set null for left and right if they don't exist
		if(r == null) {
			next_level.add(null);
			next_level.add(null);
			return;
		}
		if(r.getLeft()!= null)  
			next_level.add(r.getLeft());
		else
			next_level.add(null);
		if(r.getRight()!= null) 
			next_level.add(r.getRight());
		else
			next_level.add(null);
	}
	
	public static boolean areLeaves(int index) {
		// checks if all nodes in one_level are leaves in the dictionary
		boolean areLeaves = true;
		if(index < one_level.size()) {
			if(one_level.get(index) != null) {
				areLeaves = areLeaves && one_level.get(index).isLeaf();
				return areLeaves && areLeaves(index+1);
			}
			else {
				return areLeaves && areLeaves(index+1);
			}
		}
		return areLeaves;
	}
	
	@SuppressWarnings("unchecked")
	public void fillOneLevel(BTNode r, int index) {
		if(r == dict.getRoot()) {
			// I filled one_level the first time with root
			one_level.add(r);
			levels.add(one_level.clone());
			if(r.isLeaf())
				return;
			getNodes(one_level.get(0)); // getting children of root
			levels.add(next_level.clone());
			one_level = (ArrayList<BTNode>) next_level.clone();
			next_level.clear();
			fillOneLevel(null, index); // added the root need to move to the rest
		}
		else {
			if(areLeaves(0)) {
				// need to check 1st if all leaves then exit you're done
				return;
			}
			if(index < one_level.size()) {
				getNodes(one_level.get(index));  // CHILDREN of every specific node in one_level are saved in next_level
				fillOneLevel(null, index+1);        // 3abet all children of each node in one_level
			}
			if(!next_level.isEmpty()) {
				levels.add(next_level.clone());  // added the whole level to levels
			}
			one_level = (ArrayList<BTNode>) next_level.clone();
			next_level = new ArrayList<BTNode>();;
			fillOneLevel(null, 0);
		}
	}
	
	public void printTree() {
		if(dict.getRoot()==null) {
			System.out.println("                Dictionary is empty!");
			return;
		}
		fillOneLevel(dict.getRoot(), 0);
		System.out.println("The Dictionary tree is as follows:");
		if(levels.size() == 1) {
			// there is only one node the root
			System.out.println("                             /\\");
			System.out.println("                      "+dict.getRoot().getWord());
			System.out.println("                             ||");
			System.out.println("                             \u2014\u2014");
			return;
		}
		int depth = levels.size()-1;  // depth of tree 
		String tab = "\t";
		System.out.println(tab.repeat((int) Math.pow(2, depth))+"    /\\");
		System.out.println(print(depth, tab, 0, 0));	
		System.out.println(tab.repeat((int) Math.pow(2, depth))+"    ||");
		System.out.println(tab.repeat((int) Math.pow(2, depth))+"    \u2014\u2014");
	}
	
	@SuppressWarnings("rawtypes")
	public String print(int depth, String str, int index_level, int index_node) {
		String ans = "";
		if(depth<0)
			return ans;
		if (index_node ==0)            // printing 1st node in every level
			ans = str.repeat((int) Math.pow(2, depth));
		else {
			ans = str.repeat(((int) Math.pow(2, depth+1)));  // printing all other nodes in every level
		}
		if(index_level< levels.size() && index_node < ((ArrayList)levels.get(index_level)).size()){
			if(((ArrayList)levels.get(index_level)).get(index_node) == null) {
				// I have an empty node
				return ans + print(depth, str, index_level, index_node +1);
			}
			else {
				ans += ((BTNode)((ArrayList)levels.get(index_level)).get(index_node)).getWord();
				return ans + print(depth, str, index_level, index_node +1);
			}
		}
		else if(depth>=0){
			// i'm done with one level
			ans += "\n\n";
			return ans + print(depth-1, str, index_level+1, 0);
		}
		return ans;
	}
	
	public static void menu() {
		Dictionary dictionary = new Dictionary();
		boolean showMenu = true;
		
		while(showMenu) {		
			System.out.println("\n1.	Create the dictionary");
			System.out.println("2.	Add a definition");
			System.out.println("3.	Remove a definition");
			System.out.println("4.	Search for a definition");
			System.out.println("5.	Print dictionary");
			System.out.println("6.	Display tree");
			System.out.println("7.	Exit");
			System.out.println("- - - - - - - - - - - - - ");
			System.out.println("Enter your choice:");

			int in = checkInt(); 
			
			switch (in) {
			case 1:
				dictionary.createDictionary(dictionary);
				continue;
			case 2:		
				dictionary.addDefinition();
				continue;
			case 3:
				dictionary.removeDefinition();
				continue;
			case 4:
				dictionary.searchDef();
				continue;
			case 5:
				dictionary.getDict().inOrderPrint(dictionary.getDict().getRoot());
				continue;
			case 6:
				dictionary.printTree();
				continue;
			case 7:
				dictionary.saveFile();
				System.out.println("Program is terminated! Thank you");
				showMenu =false;
				break;
			}		
		}	
		
	}
	
	public static void main(String[] args) {
		menu();

	}

}
