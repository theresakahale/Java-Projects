package assignment5;

public class Account {
	private String account_number;
	private String type;
	private float balance;

	public Account(String acc_numb, String t, float b) {
		account_number = acc_numb;
		type = t;
		balance = b;
	}
	
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String acc_numb) {
		account_number = acc_numb;
	}

	
	public String getType() {
		return type;
	}
	public void setType(String t) {
		type = t;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float b) {
		balance = b;
	}
	@Override
	public String toString() {
		return "Account [account_number=" + account_number + ", type=" + type + ", balance=" + balance + "]";
	}
	
	
}
