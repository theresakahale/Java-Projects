/* Name: Theresa Kahale
   Date Last Modified: 18/02/2020
   Project Description: This project is used to write a program to define shapes of objects in space and store them in an array.
   Other files: EquilateralTriangle.java, Space.java, Triangle.java, Circle.java, Square.java	
 */

package assignment2;

public abstract class Shape {
	protected String color;
	protected float x_coordinate;
	protected float y_coordinate;
	
	protected Shape(String c, float x, float y) {
		color = c;
		x_coordinate = x;
		y_coordinate = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getX_coordinate() {
		return x_coordinate;
	}

	public void setX_coordinate(float x) {
		x_coordinate = x;
	}

	public float getY_coordinate() {
		return y_coordinate;
	}

	public void setY_coordinate(float y) {
		y_coordinate = y;
	}

	public abstract float area();
	
	public abstract float perimeter();
	
	public void move(int dx, int dy)
	{
		// method to move the coordinates of the shape. Takes as input deplacement of each coordinate and adds it to x and y of the shape
		x_coordinate += dx;
		y_coordinate += dy;
	}
	
	public boolean lessThan(Object objn, Object objm) {
		// compares the location of 2 objects. returns true if the x_coordinate of objn is less than y_coordinate of objm. if equal x_coordinates, 
		// returns true if y_coordinate of objn is less than objm else returns false
		if (((Shape) objn).getX_coordinate() < ((Shape)objm).getX_coordinate())
			return true;
		else if (((Shape) objn).getX_coordinate() == ((Shape)objm).getX_coordinate())
			return (((Shape) objn).getY_coordinate() < ((Shape)objm).getY_coordinate());
		else 
			return false;
	}
	
	@Override
	public String toString() {
		return ", color=" + color + ", x_coordinate=" + x_coordinate + ", y_coordinate=" + y_coordinate +"]";
	}

	@Override
	public boolean equals(Object obj) {
		// checks if an object is equal to obj. returns true if they have the same class, same x_coordinate and same y_coordinate
		return (this.getClass().equals(obj.getClass())) && (this.x_coordinate == ((Shape) obj).getX_coordinate()) && (this.y_coordinate == ((Shape) obj).getY_coordinate());
	}

}
