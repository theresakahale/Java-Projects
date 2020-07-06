package assignment5;

public class Person {
	private String name;
	private String client_number;
	private LinkedList accounts;
	
	
	public Person() {
		super();
	}
	public Person(String n, String numb, LinkedList acc) {
		name = n;
		client_number = numb;
		accounts = acc;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public String getClient_number() {
		return client_number;
	}
	public void setClient_number(String numb) {
		client_number = numb;
	}
	public LinkedList getAccounts() {
		return accounts;
	}
	public void setAccounts(LinkedList acc) {
		accounts = acc;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", client_number=" + client_number + ", accounts=" + accounts + "]";
	}
	
	
	
}
