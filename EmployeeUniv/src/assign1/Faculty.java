/* Name: Theresa Kahale
   Date Last Modified: 09/02/2020
   Project Description: This project is used to manage employees in a university. The university has 2 different types of employee: Staff or Faculty.
   Other files: University.java, Employee.java, Staff.java	
 */

package assign1;

public class Faculty extends Employee{
	protected int teaching_Load;
	public static final float bonus = 0.02f;
	
	public Faculty(String n) {
		//constructor used only to compare between employees' names and type
		super(n);
		teaching_Load = 0;
	}
	
	public Faculty(String n, float s, String d, int teaching_Load) {
		super(n, s, d);
		this.teaching_Load = teaching_Load;
	}

	public int getTeachingLoad() {
		return teaching_Load;
	}

	public void setTeachingLoad(int teaching_Load) {
		this.teaching_Load = teaching_Load;
	}

	@Override
	public String toString() {
		return "Faculty " + super.toString()+ ", teaching_Load=" + teaching_Load + "]";
	}
	
}
