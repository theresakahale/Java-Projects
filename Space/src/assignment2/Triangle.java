/* Name: Theresa Kahale
   Date Last Modified: 18/02/2020
   Project Description: This project is used to write a program to define shapes of objects in space and store them in an array.
   Other files: Shape.java, Space.java, Circle.java, EquilateralTriangle.java, Square.java	
 */

package assignment2;

public class Triangle extends Shape{
	protected float side1;
	protected float side2;
	protected float side3;
	
	
	public Triangle(String color, float x_coordinate, float y_coordinate, float s1, float s2, float s3) {
		super(color, x_coordinate, y_coordinate);
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}
	public float getSide1() {
		return side1;
	}
	public void setSide1(float side1) {
		this.side1 = side1;
	}
	public float getSide2() {
		return side2;
	}
	public void setSide2(float side2) {
		this.side2 = side2;
	}
	public float getSide3() {
		return side3;
	}
	public void setSide3(float side3) {
		this.side3 = side3;
	}
	
	public float area() {
		// returns area of the shape in float 
		float sum= (side1+side2+side3)/2;
		float area = (float) Math.sqrt(Math.abs(sum*(sum-side1)*(sum-side2)*(sum-side3)));
		return area;
	}

	public float perimeter() {
		// returns perimeter of the shape in float 
		float perimeter = side1+side2+side3;
		return perimeter;
	}
	
	@Override
	public boolean equals(Object obj) {
		// returns true if a circle is equal to obj. checks for class, x_coordinate, y_coordinate and all 3 sides
		if (super.equals(obj))
			return (this.getSide1()== ((Triangle) obj).getSide1()) && (this.getSide2()== ((Triangle) obj).getSide2()) && (this.getSide3()== ((Triangle) obj).getSide3());
		else 
			return false;
	}
	
	@Override
	public String toString() {
		return "Triangle [side1=" + side1 + ", side2=" + side2 + ", side3=" + side3 + super.toString();
	}

}
