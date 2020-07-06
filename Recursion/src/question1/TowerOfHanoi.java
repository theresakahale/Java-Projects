/* Name: Theresa Kahale
   Date Last Modified: 10/03/2020
   Project Description: This project is used to solve the mathematical problem Tower Of Hanoi either recursively or iteratively
 */
package question1;

import java.time.*;
import java.util.*;

public class TowerOfHanoi {

	public static void iterative(int n, int s, int m, int e) {
		// prints all 2^n -1 iterations for solving Tower of Hanoi of n disks and 3 pegs
		int nb_iterations = (int) Math.pow(2, n) -1;
		System.out.println(nb_iterations +" iterations!");
		
		int cur_pos_small = 0;
		
		if(n == 1) {
			// move 1 disk from start to end 
			System.out.println("Move from peg "+ s + " to peg " + e); 
		}
		
		else {
			// each ArrayList corresponds to a peg and contains all the disks 
			ArrayList<Integer> peg1 = new ArrayList<Integer>();
			ArrayList<Integer> peg2 = new ArrayList<Integer>();
			ArrayList<Integer> peg3 = new ArrayList<Integer>();

			peg1 = fillPeg(n); 				// peg 1 contains initially all the disks
			for(int i=0; i<nb_iterations; i++) {
				if((i+1)%2 ==1) {	
					// the smallest disk is moving at each odd iteration		
					// small has a disk number of 1 
					// we pop the disk number from its source
					if(cur_pos_small ==0)
						peg1.remove((Integer) 1);
					else if(cur_pos_small ==1)
						peg2.remove((Integer) 1);
					else 
						peg3.remove((Integer) 1);
					
					// the new destination of the smallest peg is calculated according to the function moveSteps 
					// the position is normalized to either 0, 1 or 2
					int new_pos_small = (cur_pos_small + moveSteps(n, 1)) %3;  		// peg 0, 1, 2
					moveOneDisk(cur_pos_small +1, new_pos_small +1);				// adding 1 just to have 1, 2, 3
					cur_pos_small = new_pos_small;
					
					// we add the smallest disk to its destination ArrayList
					if(cur_pos_small ==0) 
						peg1.add((Integer) 1);
					else if(cur_pos_small ==1) {
						peg2.add((Integer) 1);
					}
					else {
						peg3.add((Integer) 1);	
					}
				}
				
				else {
					// At even iterations, we compare the other 2 pegs that does not contain the smallest diskNumber 
					// we retrieve the minimum between them and we move the smallest disk to the other peg
					if(cur_pos_small ==0) {
						if(compareTopDisk(peg2, peg3)) {
							// I move smallest disk from peg 2 to peg 3
							int index_min = getIndexMinDisk(peg2);
							moveOneDisk(m, e);
							peg3.add((Integer) peg2.get(index_min).intValue());
							peg2.remove(index_min);			
						}
						else {
							// I move smallest disk from peg 3 to peg 2
							moveOneDisk(e, m);
							int index_min = getIndexMinDisk(peg3);
							peg2.add((Integer) peg3.get(index_min).intValue());
							peg3.remove(index_min);			
						}
					}
					if(cur_pos_small ==1) {
						if(compareTopDisk(peg1, peg3)) {
							// I move smallest disk from peg 1 to peg 3
							int index_min = getIndexMinDisk(peg1);
							moveOneDisk(s, e);
							peg3.add((Integer) peg1.get(index_min).intValue());
							peg1.remove(index_min);			
						}
						else {
							// I move smallest disk from peg 3 to peg 1
							moveOneDisk(e, s);
							int index_min = getIndexMinDisk(peg3);
							peg1.add((Integer) peg3.get(index_min).intValue());
							peg3.remove(index_min);
						}
					}
					if(cur_pos_small ==2) {
						if(compareTopDisk(peg1, peg2)) {
							// I move smallest disk from peg 1 to peg 2
							int index_min = getIndexMinDisk(peg1);
							moveOneDisk(s, m);
							peg2.add((Integer) peg1.get(index_min).intValue());
							peg1.remove(index_min);		
						}
						else {
							// I move smallest disk from peg 2 to peg 1
							moveOneDisk(m, s);
							int index_min = getIndexMinDisk(peg2);
							peg1.add((Integer) peg2.get(index_min).intValue());
							peg2.remove(index_min);	
						}
					}
				}
			}
		}	
	}
	
	public static int getIndexMinDisk(ArrayList<Integer> arr) {
		// returns the index of the minimum diskNumber in array arr
		if(arr.size() ==0)
			// if the array is empty then we return 0, to force it to become the smallest 
			return 0;
		int min = arr.get(0).intValue();
		int index_min = 0;
		
		for (int i=1 ; i<arr.size(); i++) {
			if (arr.get(i).intValue() < min){
				min = arr.get(i).intValue();
				index_min = i;
			}
		}			
		
		return index_min;
	}
	
	public static boolean compareTopDisk(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		// returns true if arr1 contains the smallest disk or if arr2 is empty, returns false in the opposite

		if(arr1.size() ==0)
			return false;
		if (arr2.size() ==0) 
			return true;
		if(arr1.get(getIndexMinDisk(arr1)) < arr2.get(getIndexMinDisk(arr2))) {
			return true;
		}
		else {		
			return false;
		}
	}
	
	public static ArrayList<Integer> fillPeg(int n){
		// returns an ArrayList of int containing all disk numbers 
		ArrayList<Integer> peg = new ArrayList<Integer>();
		for(int j=n; j>=1; j--) {
			peg.add((Integer) j);
		}
		return peg;	
		
	}
	
	public static int moveSteps(int nbDisks, int diskNumber) {
		// this method returns the number of steps the diskNumber has to move
		// if even number of disks, the even numbers will always move +2 pegs
		// 							the odd numbers will always move +1 peg
		// if odd number of disks, the odd numbers will always move in the left direction then +2
		// 							the even numbers will always move +1 pegs
		// we're using this method only to determine the movement of the smallest disk that always has a number of 1 (odd)
		
		if((nbDisks%2 ==0) && (diskNumber %2 ==1)) 
			return 1;
		else 
			return 2;	
	}
	
	public static void moveOneDisk(int i, int j) {
		// prints a single move from source i to destination j
		System.out.println("Move from peg "+ i + " to peg " + j);
	}
	
	public static void moveTower(int n, int s, int m, int e) {
		// move n disks from s to e using m as needed
		if(n == 1)
			// move 1 disk from start to end 
			moveOneDisk(s,e);
		else {
			moveTower(n-1, s, e, m);	
			moveOneDisk(s,e);
			moveTower(n-1, m, s, e);
		}
		/*
		 for n = 3, elapsed time = 0.9979 ms
		 for n = 5, elapsed time = 1.9771 ms
		 for n = 10, elapsed time = 19.9457 ms
		 for n = 100, elapsed time = forever
		 for n = 500, elapsed time = forever
		 for n = 1000, elapsed time = forever
		 */
	}
	
	public static void menu() {
		Scanner scan = new Scanner(System.in);
		boolean valid= false;
		String ans = null;
		while(true) {
			System.out.println("Do you want to solve Tower of Hanoi recursively (r) or iteratively (i)? ");
			do{
				// prompts the user to enter only either r or i 
				ans = scan.nextLine().toLowerCase();
				if (ans.equals("r") ||ans.equals("i")) {
					valid = true;
				}
				else {
					System.out.println("Recursively (r) or Iteratively (i)?");
				}
			} while (!valid);
			
			System.out.println("Enter the number of disks: ");
			int n = 0; valid = false;
			do{
				try{
					// ensures the user enters a positive number of disks
					n = Integer.parseInt(scan.nextLine());
				    valid = true;
					if (n<= 0) {
						valid = false;
						System.out.println("Enter a positive number!");
					}
				}
			    catch(NumberFormatException e) {
			    	System.out.println("Enter a number");
			    }
			} while (!valid);
			
		    
			if(ans.equals("r")) {
				// solving Tower of Hanoi recursively
			    Instant start = Instant.now();
				moveTower(n, 1, 2, 3);
			    Instant end = Instant.now();
			    double elapsed_time = Duration.between(start, end).toNanos(); 
				System.out.println("Elapsed time for " + n + " disks is " + ((double)elapsed_time/1000000.0) + " milliseconds."); 
			}
			else if (ans.equals("i")){
				// solving Tower of Hanoi iteratively
				System.out.println(n);
				iterative(n, 1,2,3);
			}
			
			else
				break;
		}
		scan.close();
	}
	
	public static void main (String[] args) {
		menu();
	}
}