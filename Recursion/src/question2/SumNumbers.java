/* Name: Theresa Kahale
   Date Last Modified: 13/03/2020
   Project Description: This project is used to solve the mathematical problem Tower Of Hanoi either recursively or iteratively
 */
package question2;

import java.util.*;

public class SumNumbers {
	
	protected static int index;
	protected static int count2 = 2;
	protected static int count = 0;
	
	public static int sumArray(int [] array) {
		// calculates sum of all elements in an array recursively for the beauty of recursion
		int sum = 0;
		for(int i = 0; i<array.length;i++)
			sum+= array[i];
		return sum;
	}
	
	public static boolean splitEqualSum(int[] array, int sum_to_be_found) {
		// takes as input the array and the half sum of the elements to be found 
		// the iterations starts at last index 
		if (sum_to_be_found == 0)
			return true;
		
		if ((index ==0) && ((sum_to_be_found) >0))
			return false;
		
		// if the following element is bigger than the sum then return false we cannot split 
		if (array[index-1] > sum_to_be_found) {
			index--;
			return false;
		}
		else {
			index--;
			return splitEqualSum(array, sum_to_be_found - array[index]);	
		}
	}
	
	public static String removeDuplicate(String s) {
		// takes as input a String s and returns a new string where all duplicates consecutive characters are removed
		s = s.toLowerCase();
		if(index <(s.length()-1)) {
			char c = s.charAt(index);
			if (c == s.charAt(index+1)) {
				s = s.substring(0, index)+ s.substring(index+1);
				index++;
				s= removeDuplicate(s);
			}
			else {
				index++;
				s= removeDuplicate(s);
			}
		}
		return s;
	}
	
	public static int fibonacci(int n) {
		// returns the nth fibonacci number and takes as input the wanted n number
		int sum = 0;
		if ((n ==1) || (n ==2)) 
			return 1;
		else 
			sum = fibonacci(n-1) + fibonacci(n-2);
		return sum;
	}


	public static boolean checkForThisIndex(int[] array, int sum, int i,int index) {
		boolean found = false;
		
		if (array.length == 0)
			return false;
		
		if (index == array.length)
			// returns false if we have reached the end of the array 
			return found;
		
		if(index == i+2)
			// comparing the element at index i to the sum 
			// if equal then we return true
			if (sum - array[i] == 0) 
				return true;
		
		if ((sum - array[i] - array[index]) == 0)
			// comparing the element at index i and index index if their sum is equal to sum
			return true;
		// if all the previous combinations were not valid, we increment index and compare it to i
		found = found || checkForThisIndex(array,sum ,i, index + 1);
		return found;
	}
	
	public static boolean sumNotAdjacent(int[] array, int sum) {
		if (array.length == 0) 
			return false;
		boolean found = false;
		
		for (int i = 0; i +2 < array.length; i++) {
			// I'm entering the (i-1) th column to check if sum is found here
			found = found || checkForThisIndex(array, sum , i, i + 2);
			if (found) 
				return found;
		}
		
		if(array.length >=2) {
			// checking if (last-1) element in the array is equal to sum  
			found = found || (array[array.length-2] ==sum);
			if(found)
				return found;
		}
		
		if(array.length >=1) {
			// checking if last element in the array is equal to sum  
			found =  found || (array[array.length-1] ==sum);
			if(found)
				return found;
		}
		
		for (int i = 0; i +2 < array.length; i++) {
			found = found || sumNotAdjacent(Arrays.copyOfRange(array, i + 2, array.length), sum - array[i]);
		}
		return found;
	}

	public static void menu() {
		boolean showMenu = true;
		Scanner scan = new Scanner(System.in);
		while(showMenu) {
			System.out.println("1.	Equal Sum");
			System.out.println("2.	Remove Duplicates");
			System.out.println("3.	Fibonacci");
			System.out.println("4.	Sums but not adjacent");
			System.out.println("5.	Exit");
			System.out.println("- - - - - - - - - - - - - ");
			System.out.println("Choose an option");

			int i = 0; boolean isNb = false;
			do {
				// checking for the validity of the user's input to make sure it's a number
				try {
					i = Integer.parseInt(scan.nextLine());
					isNb = true;
				}
				catch (NumberFormatException e) {
					System.out.println("Enter a number");
				}
			} while(!isNb);
			
			switch(i) {
				case 1:
					// choice 1 : split array into 2 equal sums 
					System.out.println("How many numbers do you want to enter? ");
					int length_array = 0; boolean isNumber = false;
					do {
						// ensuring the user's input to be a positive number
						try {
							length_array = Integer.parseInt(scan.nextLine());
							isNumber = true;
							if(length_array <=0) {
								isNumber = false;
								System.out.println("Enter a positive number");
							}
						}
						catch (NumberFormatException e) {
							System.out.println("Enter a number");
						}
					} while(!isNumber);
					
					int[] arr = new int[length_array];
					
					for(int j=0; j<arr.length; j++) {
						System.out.println("Enter number "+(j+1) +" ");
						isNb = false;
						do {
							// checking for the validity of the user's input to make sure it's a number
							try {
								arr[j] = Integer.parseInt(scan.nextLine());
								isNb = true;
							}
							catch (NumberFormatException e) {
								System.out.println("Enter a number");
							}
						} while(!isNb);
					}
					
					index= arr.length;
					int sum = sumArray(arr);	// I sum all the elements of the array 
					if(sum%2 ==0) {
						// if even sum then we check if we can divide this sum into two equal sums 
						System.out.println(splitEqualSum(arr,sum/2) +"\n");
					}
					else
						// if the elements in the array have an odd sum then we cannot split the array automatically
						System.out.println(false + "\n");
					break;
					
				case 2:
					// choice 2 : removing duplicates
					System.out.println("Enter a string ");
					String s = scan.nextLine();
					System.out.println("We will remove consecutive duplicate characters from \"" + s + "\"");
					index = 0;
					System.out.println("The new string is \"" +removeDuplicate(s) +"\"\n");
					break;
					
				case 3:
					// choice 3: fibonacci
					boolean is_aNumber = false; int n = 0;
					do {
						try {
							System.out.println("Enter a positive number");
							n = Integer.parseInt(scan.nextLine());
							is_aNumber = true;
							if(n <=0) 
								is_aNumber =false;
						}
						catch(NumberFormatException e) {
							is_aNumber =false;
						}
					} while(!is_aNumber);
					System.out.println("The nth Fibonacci number is " + fibonacci(n) + "\n");
					break;
					
				case 4:
					// choice 4: finding a sum in array without taking adjacent elements
					System.out.println("How many numbers do you want to enter? ");
					int arr_length = 0; boolean isANumber = false;
					do {
						// ensuring a positive length of array 
						try {
							arr_length = Integer.parseInt(scan.nextLine());
							isANumber = true;
							if(arr_length <=0) {
								isANumber = false;
								System.out.println("Enter a positive number");
							}
						}
						catch (NumberFormatException e) {
							System.out.println("Enter a number");
						}
					} while(!isANumber);
					
					int[] array = new int[arr_length];
					for(int j=0; j<array.length; j++) {
						System.out.println("Enter number "+(j+1) +" ");
						isNb = false;
						do {
							// checking for the validity of the user's input to make sure it's a number
							try {
								array[j] = Integer.parseInt(scan.nextLine());
								isNb = true;
							}
							catch (NumberFormatException e) {
								System.out.println("Enter a number");
							}
						} while(!isNb);
					}
					
					System.out.println("Enter the sum you wish to find in the array ");
					isANumber = false; int i_sum = 0;
					do {
						try {
							i_sum = Integer.parseInt(scan.nextLine());
							isANumber = true;
						}
						catch (NumberFormatException e) { 
							System.out.println("Enter a number");
						}
					} while(!isANumber);
					index = count =0;
					System.out.println(sumNotAdjacent(array, i_sum) +"\n");
					count2 = 2;
					break;
					
				case 5:
					// exiting the program
					System.out.println("You have exited the program!");
					showMenu= false;
				
				default:
					break;
			}
		}
		scan.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
	}

}
