/* Name: Theresa Kahale
   Date Last Modified: 18/02/2020
   Project Description: This project is used to write a program to define shapes of objects in space and store them in an array.
   Other files: Shape.java, Space.java, Triangle.java, Circle.java, Square.java	
 */

package assignment2;

public class EquilateralTriangle extends Triangle{
	protected float equal_side;

	public EquilateralTriangle(String color, float x_coordinate, float y_coordinate, float s1) {
		super(color, x_coordinate, y_coordinate, s1, s1, s1);
		equal_side = s1;
	}
	
	@Override
	public float area() {
		double area = Math.sqrt(3)*equal_side*equal_side/4;
		return (float) area;
	}

	@Override
	public String toString() {
		return "Equilateral Triangle [side=" + equal_side + ", color=" + color + ", x_coordinate=" + x_coordinate + ", y_coordinate=" 
				+ y_coordinate + "]";
	}

	
	
}
