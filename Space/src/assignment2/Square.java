/* Name: Theresa Kahale
   Date Last Modified: 18/02/2020
   Project Description: This project is used to write a program to define shapes of objects in space and store them in an array.
   Other files: Shape.java, Space.java, Triangle.java, EquilateralTriangle.java, Circle.java	
 */

package assignment2;

public class Square extends Shape{
	protected float side_length;

	public Square(String color, float x_coordinate, float y_coordinate, float s_l) {
		super(color, x_coordinate, y_coordinate);
		side_length = s_l;
	}

	public float getSide_length() {
		return side_length;
	}

	public void setSide_length(float side_length) {
		this.side_length = side_length;
	}
	
	public float area() {
		// returns area of the shape in float 
		float area = side_length*side_length;
		return area;
	}

	public float perimeter() {
		// returns perimeter of the shape in float 
		float perimeter = 4*side_length;
		return perimeter;
	}
	
	@Override
	public boolean equals(Object obj) {
		// returns true if a square is equal to obj. checks for class, x_coordinate, y_coordinate and side length
		if (super.equals(obj))
			return (this.getSide_length()== ((Square) obj).getSide_length());
		else 
			return false;
	}

	@Override
	public String toString() {
		return "Square [side_length=" + side_length + super.toString();
	}

}
