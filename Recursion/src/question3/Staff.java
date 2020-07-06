/* Name: Theresa Kahale
   Date Last Modified: 10/03/2020
   Project Description: This project is used to manage employees in a university. The university has 2 different types of employee: Staff or Faculty.
   Other files: Employee.java, Staff.java, Faculty.java, Services.java
 */

package question3;

public class Staff extends Employee{

	public Staff(String a) {
		//constructor used only to compare between employees' names and type
		super(a);
	}
	
	public Staff(String n, float s, String d) {
		super(n, s, d);
	}

	@Override
	public String toString() {
		return "Staff "+ super.toString() + "]";
	}
	
}
