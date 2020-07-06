/* Name: Theresa Kahale
   Date Last Modified: 10/03/2020
   Project Description: This project is used to manage employees in a university. The university has 2 different types of employee: Staff or Faculty.
   Other files: Employee.java, Staff.java, Faculty.java, University.java
 */
package question3;

public interface Services {
	/*
	 * Classes implementing the interfaces are forced to implement all the abstract methods in the interface.
	 * It's a guarantee if someone decided to reuse my defined interface.
	 * Classes' implementation of these methods may have a different way of defining it. 
	 * This strengthens the flexible use case of the interface as it allows for other developpers to define their own version of 
	 * implementing all the methods in the interface. 
	 */
	
	public void addEmployee(String n, float s, String d, String type_emp, int teaching_load);
	
	public void delEmployee(int index);

	public void raiseSalary(float percentage, int index);
	
	public String checkIfFound(String name, String emp_t);
	
}
