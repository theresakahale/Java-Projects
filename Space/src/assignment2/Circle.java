/* Name: Theresa Kahale
   Date Last Modified: 18/02/2020
   Project Description: This project is used to write a program to define shapes of objects in space and store them in an array.
   Other files: Shape.java, Space.java, Triangle.java, EquilateralTriangle.java, Square.java	
 */

package assignment2;

public class Circle extends Shape{
	protected float radius;

	public Circle(String color, float x_coordinate, float y_coordinate, float r) {
		super(color, x_coordinate, y_coordinate);
		radius = r;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	@Override
	public void move(int dx, int dy) {
		System.out.println("You cannot move this circle!");
	}

	@Override
	public void setX_coordinate(float x) {
	}

	@Override
	public void setY_coordinate(float y) {
	}

	public float area() {
		// returns area of the shape in float 
		float area = (float) (4*Math.PI*radius*radius);
		return area;
	}

	public float perimeter() {
		// returns perimeter of the shape in float 
		float perimeter = (float) (2*Math.PI*radius);
		return perimeter;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// returns true if a circle is equal to obj. checks for class, x_coordinate, y_coordinate and radius
		if (super.equals(obj))
			return (this.getRadius()== ((Circle) obj).getRadius());
		else 
			return false;
	}

	@Override
	public String toString() {
		return "Circle [radius=" + radius + super.toString();
	}
	
}
