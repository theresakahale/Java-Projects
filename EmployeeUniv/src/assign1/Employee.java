/* Name: Theresa Kahale
   Date Last Modified: 09/02/2020
   Project Description: This project is used to manage employees in a university. The university has 2 different types of employee: Staff or Faculty.
   Other files: University.java, Staff.java, Faculty.java	
 */

package assign1;

public class Employee {
	protected String name;
	protected float salary;
	protected String dateOfRecruitment;
	
	public Employee(String a) {
		//constructor used only to compare between employees' names and type
		name = a;
		salary = 0.0F;
		dateOfRecruitment = "";
	}
	
	public Employee(String a, float s, String d) {
		name = a;
		salary = s;
		dateOfRecruitment = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		name = n;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float s) {
		salary = s;
	}

	public String getDateOfRecruitment() {
		return dateOfRecruitment;
	}

	public void setDateOfRecruitment(String d) {
		dateOfRecruitment = d;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", salary=" + salary + ", dateOfRecruitment=" + dateOfRecruitment;
	}

	@Override
	public boolean equals(Object obj) {
		return (this.name.equals(((Employee) obj).getName())) && (this.getClass().equals(obj.getClass()));
	}
	
	
}
