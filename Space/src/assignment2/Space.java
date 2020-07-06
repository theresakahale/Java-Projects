/* Name: Theresa Kahale
   Date Last Modified: 18/02/2020
   Project Description: This project is used to write a program to define shapes of objects in space and store them in an array.
   Other files: Shape.java, Circle.java, Triangle.java, EquilateralTriangle.java, Square.java	
 */

package assignment2;

import java.io.*;
import java.util.Scanner;

public class Space {
	protected Shape[] shapes;
	public static int counter_shape;

	public Space(Shape[] original_shapes) {
		shapes = original_shapes;
		counter_shape = 0;
	}

	public Shape[] ChangeSizeArray(Shape[] s_in) { 
		// increments the size of array s_in of type Shape by 1 
		Shape[] temp_shapes = new Shape[counter_shape + 1]; 
		for (int i = 0; i < counter_shape; i++) {
			temp_shapes[i] = s_in[i]; // na2alet l old
		}
		counter_shape++; // sar counter 2
		s_in = temp_shapes;
		return s_in;
	}

	public static void main(String[] args) throws IOException{
		Shape[] shapes= new Shape[1];
		Space sp = new Space(shapes);
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(System.in);
		while(true) {
			System.out.println("\n1. Add a shape");
			System.out.println("2. Delete a shape");
			System.out.println("3. Compute Area and Perimeter");
			System.out.println("4. Display All");
			System.out.println("5. Read from file");
			System.out.println("6. Exit");
			System.out.println("- - - - - - - -");
			System.out.println("Enter your choice:");
			String i = scan1.nextLine();
			try {
				if (Integer.parseInt(i)==1) {
					boolean flag = true;
					while(flag)
					{
						System.out.println("\nA. Add a Circle");
						System.out.println("B. Add a Square");
						System.out.println("C. Add a Triangle");
						System.out.println("D. Return to main menu");
						System.out.println("- - - - - - - -");
						System.out.println("Enter shape:");
						String ans= scan.next();
						
						if(ans.equalsIgnoreCase("A")) 
						{	// adding a circle 
							System.out.println("Enter the radius of the circle: ");
							float radius = -1; boolean is_radius = true;
							do{
								// type and sign checking of radius. keeps on asking for a positive integer for the radius of the circle 
								try{
									radius = Integer.parseInt(scan1.nextLine());
									is_radius= false;
									if(radius <= 0)
									{	is_radius  = true;
										System.out.println("Enter a positive radius");}
								}
								catch(NumberFormatException e) {
									System.out.println("Enter a number");
								}
							} while(is_radius);
							System.out.println("Enter the x coordinate of the center of the circle: ");
							float x_coordinate = Float.parseFloat(scan1.nextLine());
							System.out.println("Enter the y coordinate of the center of the circle: ");
							float y_coordinate = Float.parseFloat(scan1.nextLine());
							System.out.println("Enter the color of the circle: ");
							String color = scan1.nextLine();
							if (counter_shape == 0) 
							{
								// no need to increment size of array shapes when adding the 1st shape 
								shapes = new Shape[1];
								shapes[counter_shape] = new Circle(color, x_coordinate, y_coordinate, radius);
								counter_shape++;
								System.out.println("Number of shapes currently "+ counter_shape);
								System.out.println("Circle Added! " + shapes[0]); 
							}
							else 
							{	// incrementing the size of array shapes if not adding the 1st shape then adding a new circle 
								shapes = sp.ChangeSizeArray(shapes);
								System.out.println("Number of shapes currently "+ shapes.length);
								shapes[counter_shape-1] = new Circle(color, x_coordinate, y_coordinate, radius);  
								System.out.println("Circle Added! " + shapes[counter_shape-1]);
							}
							continue;
						}
						else if (ans.equalsIgnoreCase("B")) 
						{
							System.out.println("Enter the side length of the square: ");
							float side_length = -1; boolean is_side = true;
							do{
								// type and sign checking of length. keeps on asking for a positive integer for the side length of the square 
								try{
									side_length = Integer.parseInt(scan1.nextLine());
									is_side= false;
									if(side_length <= 0)
									{	is_side  = true;
										System.out.println("Enter a positive side length");}
								}
								catch(NumberFormatException e) {
									System.out.println("Enter a number");
								}
							} while(is_side);
							System.out.println("Enter the x coordinate of the square: ");
							float x_coordinate = scan.nextFloat();
							System.out.println("Enter the y coordinate of the square: ");
							float y_coordinate = scan.nextFloat();
							System.out.println("Enter the color of the square: ");
							String color = scan1.nextLine();
							if (counter_shape == 0)  
							{
								// no need to increment size of array shapes when adding the 1st shape
								shapes = new Shape[1];
								shapes[counter_shape] = new Square(color, x_coordinate, y_coordinate, side_length);
								counter_shape++;
								System.out.println("Number of shapes currently "+ counter_shape);
								System.out.println("Square Added! " + shapes[0]);
							}
							else 
							{ 	// incrementing the size of array shapes if not adding the 1st shape then adding a new square 
								shapes = sp.ChangeSizeArray(shapes);
								shapes[counter_shape-1] = new Square(color, x_coordinate, y_coordinate, side_length); // last elt 3am bhoto new shape 
								System.out.println("Number of shapes currently "+ counter_shape);
								System.out.println("Square Added! " + shapes[counter_shape-1]);
							}
							continue;
						}
						else if (ans.equalsIgnoreCase("C")) 
						{
							System.out.println("Enter the x coordinate of the triangle: ");
							float x_coordinate = scan.nextFloat();
							System.out.println("Enter the y coordinate of the triangle: ");
							float y_coordinate = scan.nextFloat();
							float side1= -1, side2=-1, side3=-1;
							while(true)
							{
								System.out.println("Enter the length of side 1: ");
								boolean is_side1 = true;
								do{
									// type and sign checking of 1st side. keeps on asking for a positive integer for the side length of the triangle 
									try{
										side1 = Integer.parseInt(scan1.nextLine());
										is_side1= false;
										if(side1 <= 0)
										{	is_side1  = true;
											System.out.println("Enter a positive side length");}
									}
									catch(NumberFormatException e) {
										System.out.println("Enter a number");
									}
								} while(is_side1);
								System.out.println("Enter the length of side 2: ");
								boolean is_side2 = true;
								do{
									// type and sign checking of 2nd side. keeps on asking for a positive integer for the side length of the triangle 
									try{
										side2 = Integer.parseInt(scan1.nextLine());
										is_side2= false;
										if(side2 <= 0)
										{	is_side2  = true;
											System.out.println("Enter a positive side length");}
									}
									catch(NumberFormatException e) {
										System.out.println("Enter a number");
									}
								} while(is_side2);
								System.out.println("Enter the length of side 3: ");
								boolean is_side3 = true;
								do{
									// type and sign checking of 3rd side. keeps on asking for a positive integer for the side length of the triangle 
									try{
										side3 = Integer.parseInt(scan1.nextLine());
										is_side3= false;
										if(side3 <= 0)
										{	is_side3  = true;
											System.out.println("Enter a positive side length");}
									}
									catch(NumberFormatException e) {
										System.out.println("Enter a number");
									}
								} while(is_side3);								
								if (((side1+side2)> side3) && ((side1+side3)>side2) && ((side3+side2)>side1))
									break;
								else
									System.out.println("Impossible Triangle! Enter again: ");
							}
							
							System.out.println("Enter the color of the triangle: ");
							String color = scan1.nextLine();
							if (counter_shape == 0) 
							{
								// no need to increment size of array shapes when adding the 1st shape
								shapes = new Shape[1];
								if (side1 != side2) // if not equal sides then it's a normal triangle, else it's equilateral
									shapes[counter_shape] = new Triangle(color, x_coordinate, y_coordinate, side1, side2, side3);
								else
									shapes[counter_shape] = new EquilateralTriangle(color, x_coordinate, y_coordinate, side1);
								counter_shape++;
								System.out.println("Number of shapes currently "+ counter_shape);
								System.out.println("Triangle Added! " + shapes[0]);
							}
							else 
							{ 	// incrementing the size of array shapes if not adding the 1st shape then adding a new circle 
								shapes = sp.ChangeSizeArray(shapes);
								if (side1 != side2) // if not equal sides then it's a normal triangle, else it's equilateral
									shapes[counter_shape-1] = new Triangle(color, x_coordinate, y_coordinate, side1, side2, side3);
								else
									shapes[counter_shape-1] = new EquilateralTriangle(color, x_coordinate, y_coordinate, side1);	
								System.out.println("Number of shapes currently "+ counter_shape);
								System.out.println("Triangle Added! " + shapes[counter_shape-1]);
							}
							continue;
						}
						else if (ans.equalsIgnoreCase("D")) 
						{	// return to main menu, needs to go back to previous menu
							flag = false;
							break;
						}
						else 
						{
							System.out.println("Wrong input! Choose only between A-B-C-D: ");
							continue;
						}
					}
					// to show the main menu
					if(!flag)
						continue;
				}
				else if (Integer.parseInt(i)==2)
				{
					// checks for the occurrence of shapes whose x and y are the same as those specified by the user
					// deletes objects at x and y coordinates specified by the user 
					boolean is_found = false;
					while(!is_found)
					{
						System.out.println("Enter the x coordinate at which you wish to delete objects: ");
						float x_coordinate = scan.nextFloat();
						System.out.println("Enter the y coordinate at which you wish to delete objects: ");
						float y_coordinate = scan.nextFloat();
						int shapes_to_del = 0;
						for(int i1=0; i1<counter_shape; i1++)
						{
							// gets the occurrences of how many shapes have x and y the same as those specified by the user
							if((shapes[i1].getX_coordinate() == x_coordinate) && (shapes[i1].getY_coordinate() == y_coordinate))
								shapes_to_del++;
						}
						for(int j1=0; j1<shapes_to_del; j1++)
						{	// loops as much as there are elements to delete
							for(int i1=0; i1<counter_shape; i1++)
							{	// looping over all shapes in shape, removing each shape at each time, decreasing the size of array shapes at each 
								// time we delete one shape
								if((shapes[i1].getX_coordinate() == x_coordinate) && (shapes[i1].getY_coordinate() == y_coordinate)) 
								{
									is_found = true;
									System.out.println("Element to be removed: "+ shapes[i1]); 
									shapes[i1] = shapes[counter_shape-1];
									shapes[counter_shape-1] = null; 
									counter_shape--;
									Shape[] temp_shapes = new Shape[counter_shape]; 
									for(int j=0; j<counter_shape;j++) 
									{
										temp_shapes[j]= shapes[j];
									}
									shapes =temp_shapes;
								}
							}
						}
						if(!is_found)
						{	
							System.out.println("This object does not exist!");
						}
					}
				}
				else if (Integer.parseInt(i)==3)
				{	
					// calculates the area and perimeter of all the shapes that have x and y the same as those specified by the user
					boolean is_found = false;
					while(!is_found)
					{	System.out.println("Enter the x coordinate of the object: ");
						float x_coordinate = scan.nextFloat();
						System.out.println("Enter the y coordinate of the object: ");
						float y_coordinate = scan.nextFloat();
						for(int i1=0; i1<counter_shape; i1++)
						{
							if((shapes[i1].getX_coordinate() == x_coordinate) && (shapes[i1].getY_coordinate() == y_coordinate)) 
							{
								is_found = true;
								System.out.println(shapes[i1]);
								System.out.println("Area= " + shapes[i1].area());
								System.out.println("Perimeter= " + shapes[i1].perimeter()+"\n");
							}
						}
						if(!is_found)
						{	
							System.out.println("This object does not exist!");
						}
					}
					continue;
				}
				else if (Integer.parseInt(i)==4)
				{	// displays all the information of all the shapes existing in array shapes
					if(counter_shape>0)
					{	for(Shape s : shapes)
							System.out.println(s +"\n");}
					else
						System.out.println("There are no shapes available!");
				}
				else if (Integer.parseInt(i)==5)
				{	
					// reads from text file "input.txt" that contains information about shapes
					// creates each object existing in the file 
					int line_nb =0;
					try 
					{
						FileReader fr = new FileReader(new File("input.txt"));
						BufferedReader br = new BufferedReader(fr);
						String str = null; String[] new_shape= null;
						while ((str = br.readLine()) != null) 
						{
							line_nb++;
							new_shape = str.split(", ");
							if ((new_shape.length <5) || (new_shape.length >7))
								// loop to check if any information is missing for the shape to be created
							{	System.out.println("Line "+ line_nb + " contains invalid information!");
								continue;
							}
							try 
							{
								// checks if any of the description of the shape is wrong in the text file
								float x_coord = Float.parseFloat(new_shape[2]);
								float y_coord = Float.parseFloat(new_shape[3]);
								if (new_shape[0].equalsIgnoreCase("circle") && (new_shape.length ==5))
								{	
									// increasing size of array shapes and adding a new circle based on specification from the text file
									float radius = Float.parseFloat(new_shape[4]);
									shapes = sp.ChangeSizeArray(shapes);
									shapes[counter_shape-1] = new Circle(new_shape[1], x_coord, y_coord, radius);
									System.out.println("Read Shape! "+ shapes[counter_shape-1]);
							
								}
								if (new_shape[0].equalsIgnoreCase("square") && (new_shape.length ==5))
								{	
									// increasing size of array shapes and adding a new square based on specification from the text file
									float length_side = Float.parseFloat(new_shape[4]);
									shapes = sp.ChangeSizeArray(shapes);
									shapes[counter_shape-1] = new Square(new_shape[1], x_coord, y_coord, length_side);
									System.out.println("Read Shape! "+ shapes[counter_shape-1]);
	
								}
								if (new_shape[0].equalsIgnoreCase("triangle") && (new_shape.length ==7))
								{	
									// increasing size of array shapes and adding a new triangle based on specification from the text file
									float side_1 = Float.parseFloat(new_shape[4]);
									float side_2 = Float.parseFloat(new_shape[5]);
									float side_3 = Float.parseFloat(new_shape[6]);
									shapes = sp.ChangeSizeArray(shapes);
									shapes[counter_shape-1] = new Triangle(new_shape[1], x_coord, y_coord, side_1, side_2, side_3);
									System.out.println("Read Shape! "+ shapes[counter_shape-1]);
								}	
								if (new_shape[0].equalsIgnoreCase("equilateral triangle") && (new_shape.length ==5))
								{	
									// increasing size of array shapes and adding a new equilateral triangle based on specification from the text file
									float length_side = Float.parseFloat(new_shape[4]);
									shapes = sp.ChangeSizeArray(shapes);
									shapes[counter_shape-1] = new EquilateralTriangle(new_shape[1], x_coord, y_coord, length_side);
									System.out.println("Read Shape! "+ shapes[counter_shape-1]);
								}
							}
							catch(NumberFormatException e) 
							{	System.out.println("Line "+ line_nb + " contains invalid information!");}
						}
						br.close();
	
					} 
					catch (FileNotFoundException e)
					{
						System.out.println("File \"Input.txt\" is not found in the directory!");
					}				
				}
				else if(Integer.parseInt(i)==6)
				{ 	// writes all existent shapes in a text file output.txt and exits program
					FileWriter fw = new FileWriter(new File("output.txt"));
					BufferedWriter bw = new BufferedWriter(fw);
					if(counter_shape>0) {
						for(int i1=0; i1<shapes.length;i1++)
						{
							bw.write(shapes[i1].toString()+"\n");
						}
					}
					else
						bw.write("No Shapes Available!");
					System.out.println("All the shapes can be found in \"output.txt\"!\n");
					bw.close();
					break;
				}
				else 
				{	System.out.println("Wrong input! Choose between 1-2-3-4-5-6:");
					continue;
				}
			}
			catch(NumberFormatException e) 
			{
				e.printStackTrace();
				System.out.println("Wrong input! Choose between 1-2-3-4-5-6");
				continue;
			}

		}
		scan.close();
		scan1.close();
	}

}
