/* Name: Theresa Kahale
   Date Last Modified: 10/03/2020
   Project Description: This project is used to manage employees in a university. The university has 2 different types of employee: Staff or Faculty.
   Other files: Employee.java, Staff.java, Faculty.java, Services.java
 */

package question3;

import java.util.ArrayList;
import java.util.Scanner;

public class University implements Services{
	
	public static int size_university;
	private ArrayList<Employee> emp;
	
	public University() {
		emp = new ArrayList<Employee>();
		size_university =0;
	}

	public ArrayList<Employee> getEmp() {
		return emp;
	}

	public void setEmp(ArrayList<Employee> e) {
		emp = e;
	}
	
	public void addEmployee(String n, float s, String d, String type_emp, int teaching_load) {
		// adds a new employee with a name n, date of recruitment d, type (if faculty or staff), his teaching load if he's a faculty
		// to the array emp in University 
		if(type_emp.equalsIgnoreCase("s")) {
			emp.add(new Staff(n, s, d));
		}
		else {
			emp.add(new Faculty(n, s, d, teaching_load));
		}
		size_university++;
	}
	
	public void delEmployee(int index) {
		// deletes an employee at index in the array emp in University 
		emp.remove(index);
		size_university--;
	}

	public void raiseSalary(float percentage, int index) {
		// raises salary by a percentage of an employee at index in array emp 
		// and adds a bonus to the salary if the employee is a faculty 
		if(emp.get(index) instanceof Staff) {
			emp.get(index).setSalary((emp.get(index).getSalary())*(1+percentage/100));
		}
		else {
			emp.get(index).setSalary(((emp.get(index).getSalary())*(1+percentage/100))*(1+Faculty.bonus));
		}
	}
	
	public String checkIfFound(String name, String emp_t){
		// checks if an employee of name and type emp_t already exists in array emp 
		// return true+index at which it's found, false+(-1) otherwise
		Employee e;
		if ((emp_t.equals("assign1.Faculty")) || (emp_t.equals("f")))
			e = new Faculty(name);
		else 
			e = new Staff(name);
		String found = "false"; int index_emp = -1;
		for(int i1=0; i1<size_university; i1++) {
			if(emp.get(i1).equals(e)) {
				index_emp = i1;
				found = "true";
				break;
			}
		}
		return found+ index_emp;
	}
	
	@Override
	public String toString() {
		return "[emp=" + emp + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		University uni= new University();
		int counter = 0; 	// counter to check nb of false inputs the user enters
		Scanner scan = new Scanner(System.in);
		Scanner scan_int = new Scanner(System.in);
		while (true) {
			System.out.println("\n1.Add employee");
			System.out.println("2.Delete employee");
			System.out.println("3.List all");
			System.out.println("4.Raise Salary");
			System.out.println("5.Exit");
			System.out.println("----------------------");
			System.out.println("Enter your choice:");
			
			int i = scan_int.nextInt();
			
			if (i==1) {
				// Add Employee Option
				counter = 0;
				boolean emp_found = false;
				System.out.print("\nName of Employee you wish to add: ");
				String name = scan.nextLine();
				System.out.print("Faculty(f) or Staff(s): ");
				String type_employee = scan.nextLine();
				System.out.print("Date of Recruitment of Employee dd-mm-yy: ");
				String dateOfRecruitment = scan.nextLine();
				System.out.print("Salary of Employee: ");
				float salary = scan_int.nextFloat();
				int teaching_load = 0;
				Employee e;
				if (type_employee.equalsIgnoreCase("f")){
					// asks for the teaching load if the employee is a Faculty
					System.out.print("Teaching Load of Employee: ");
					teaching_load = scan_int.nextInt();
					e = new Faculty(name, salary,dateOfRecruitment, teaching_load);
				}
				else {
					e = new Staff(name, salary,dateOfRecruitment);
				}
				for(int k=0; k<size_university; k++) {
					// checks if the employee created already exists in the arraylist emp
					if(uni.getEmp().get(k).equals(e)) {
						emp_found= true;
						System.out.println("Employee "+ name+ " already exists");
						break;
					}
				}
				if (emp_found)
					continue;
				
				uni.addEmployee(name, salary, dateOfRecruitment, type_employee, teaching_load);
				System.out.println("Employee Added!");
			}
			else if(i==2) {
				// Delete Employee Option
				counter= 0;
				boolean found = false;
				String type_e = "";	int count_name = 0;
				while(!found) {
					System.out.print("Name of Employee you wish to delete: ");
					String name_to_del = scan.nextLine();
					for(int i1=0; i1<size_university;i1++) {
						// searches in ArrayList emp if the name of the employee exists and counts its appearance
						if(uni.getEmp().get(i1).getName().equals(name_to_del)) {
							count_name++;
						}
					}
					for(int i1=0; i1<size_university;i1++) {
						// asks for the type of the employee if he appears more than once else I get the name of class : assign1.Staff or assign1.Faculty
						if(uni.getEmp().get(i1).getName().equals(name_to_del)) {
							if (count_name>1) {
								System.out.print("Employee "+ name_to_del + " is a Faculty(f) or Staff(s) ");
								type_e = scan.nextLine();
							}
							else 
								type_e = uni.getEmp().get(i1).getClass().getName();
							String found_emp = uni.checkIfFound(name_to_del, type_e);
							if(found_emp.startsWith("true")) {
								found= true; int index_emp = Integer.parseInt(found_emp.substring(4));
								System.out.println("Do you wish to delete this employee (y/n)? "+ uni.getEmp().get(index_emp));
								String ans = scan.nextLine();
								if (ans.equals("y")) {
									uni.delEmployee(index_emp);
									System.out.println("Employee "+ name_to_del + " has been successfully deleted!");
								}
								else 
									System.out.println("Employee "+ name_to_del + " will not be deleted!");
								break;
							}
						}
					}
					if(!found)
						System.out.println("Employee "+ name_to_del+ " has not been found!");
				}
			}
			else if (i==3) {
				// List Option -- lists all employees faculty first then Staff
				counter= 0;
				System.out.println("Faculty First!");
				for(int i1=0; i1<size_university;i1++) {
					if(uni.getEmp().get(i1) instanceof Faculty) {
						System.out.println(uni.getEmp().get(i1) + "\n");
					}
				}
				System.out.println("Staff Next!");
				for(int j=0; j<size_university;j++) {
					if(uni.getEmp().get(j) instanceof Staff) {
						System.out.println(uni.getEmp().get(j) + "\n");
					}
				}
			}
			else if (i==4) {
				// Raise Salary Option
				counter= 0;
				boolean found = false;
				String type_employee = "";	int name_count = 0;
				while(!found) {
					System.out.print("Name of Employee you wish to raise his/her salary: ");
					String name_to_raise = scan.nextLine();
					for(int i1=0; i1<size_university;i1++) {
						// searches in arraylist emp if the name of the employee exists and counts its appearance
						if(uni.getEmp().get(i1).getName().equals(name_to_raise)) {
							name_count++;
						}
					}
					for(int i1=0; i1<size_university;i1++) {
						// asks for the type of the employee if he appears more than once else I get the name of class : assign1.Staff or assign1.Faculty
						if(name_count>1) {
							System.out.print("Employee "+ name_to_raise + " is a Faculty(f) or Staff(s) ");
							type_employee = scan.nextLine();
						}
						else 
							type_employee = uni.getEmp().get(i1).getClass().getName();
						String found_emp = uni.checkIfFound(name_to_raise, type_employee);
						if(found_emp.startsWith("true")) {
							found= true; int index_emp = Integer.parseInt(found_emp.substring(4));
							System.out.print("Enter the percentage by which the salary is to be raised (0-100): ");
							float percentage = scan.nextFloat();
							System.out.println("Old Salary "+ uni.getEmp().get(index_emp).getSalary());
							uni.raiseSalary(percentage, index_emp);
							System.out.println("New Salary "+ uni.getEmp().get(index_emp).getSalary());
							break;
						}	
					}	
					if(!found)
						System.out.println("Employee "+ name_to_raise+ " has not been found!");
				}
			}
			else if(i==5) {
				//Exit Program Option by choice 
				counter= 0;
				System.out.println("You have exited the program! Enjoy your day!");
				break;
			}
			else {
				// counts the number of input errors and returns how many trials are left for the user before exiting the program
				System.out.println("Error! \nChoose between options 1-2-3-4-5 only");
				counter++;
				System.out.println("You have " + (5-counter) +" trials left before exiting the program");
				if (counter==5) {
					// exits program if the user enters 5 consecutive errors
					System.out.println("You entered 5 wrong inputs, thus have exited the program! Enjoy your day!");
					break;
				}
			}
		}
		scan.close(); scan_int.close();
	}

}
