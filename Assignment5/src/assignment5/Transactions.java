package assignment5;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transactions {
	private Person person;
	private String date;
	private String time;
	private String type;
	private String from_account_chosen;
	private String to_account_chosen;
	private float amount;
	
	public Transactions() {
		time = saveTime();
		date = saveDate();
	}

	public static String saveTime() {
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		return time.format(date);
	}
	
	public static String saveDate() {
		Date date = new Date();
		SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
		return day.format(date);
	}

	public String getFrom_account_chosen() {
		return from_account_chosen;
	}

	public void setFrom_account_chosen(String from) {
		from_account_chosen = from;
	}

	public String getTo_account_chosen() {
		return to_account_chosen;
	}

	public void setTo_account_chosen(String to) {
		to_account_chosen = to;
	}

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person p) {
		person = p;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String d) {
		date = d;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String t) {
		time = t;
	}
	public String getType() {
		return type;
	}
	public void setType(String t) {
		type = t;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amt) {
		amount = amt;
	}

	@Override
	public String toString() {
		return "Transactions [Name:" + person.getName()+", Client Number" + person.getClient_number()+"]" + "\ndate=" + date + ", time=" + time + ", type=" + type
				+ ", from_account=" + from_account_chosen + ", to_account=" + to_account_chosen
				+ ", amount=" + amount;
	}
	
	
	
}
