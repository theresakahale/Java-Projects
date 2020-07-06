/* Name: Theresa Kahale
   Date Last Modified: 28/04/2020
   Project Description: This project is used to design and implement a simulated Automated Teller Machine (ATM).
    					Transactions between 8:30 AM and 6:00 PM are processed in the same day. 
    					Other transactions are saved in the queue and processed next day between 8:00 and 8:30 AM before 
    					the bank reopens.
   Other files: Transactions.java, Account.java, Queue.java, Person.java, Node.java, LinkedList.java
 */

package assignment5;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ATM {
	private Queue transactions;
	private static Person[] persons;
	private static String[] IDs;
	private static String[] pass;
	private static Scanner scan = new Scanner(System.in);
	private static int index = -1;

	public ATM() {
		transactions = new Queue();
		try {
			fillIDPass("List_ID_Pass.txt");
			fillPersons("Database.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fillIDPass(String file) throws IOException{
		try {
			FileReader fr = new FileReader(new File(file));
			BufferedReader br = new BufferedReader(fr);
			String[] info;
			String id ="";
			String Pass ="";
			String line;
			while ((line = br.readLine())!=null) {
				info = line.split(",");
				if(info.length ==2) {
					id += info[0] + ",";
					Pass += info[1] + ",";
				}
			}
			IDs = id.split(",");
			pass = Pass.split(",");
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File of Identification number and Passwords is not found!");
		}
	}

	public static boolean validateIDPass(String id_nb, String password) {
		boolean found = false;
		int i;
		for(i=0; i<IDs.length;i++) {
			if(IDs[i].equals(id_nb)) {
				found = true;
				break;
			}
		}
		if(!found)
			return false;
		if(pass[i].equals(password)) {
			found = true;
			index = i;
		}
		else {
			found = false;
			index = -99;
		}
		return found;
	}
	
	public static void fillPersons(String file){
		try {
			FileReader fr = new FileReader(new File(file));
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			persons = new Person[IDs.length];
			String[] info;
			String line;
			int counter = 0;
			while ((line = br.readLine())!=null) {
				info = line.split(",");
				if((info.length-2)%3 !=0) {
					System.out.println("Something is missing in File Database.txt line "+(counter+1));
					System.out.println("Please check before opening the bank");
					return;
				}
				if(counter== IDs.length) {
					System.out.println("Something is missing in File List_ID_Pass.txt");
					System.out.println("Please check before opening the bank");
					return;
				}
				persons[counter] = new Person();
				persons[counter].setName(info[0]);
				persons[counter].setClient_number(info[1]);
				LinkedList accs = new LinkedList();
				for(int i=0; i<((info.length-2)/3);i++){
					accs.add(new Account(info[2+3*i],info[2+3*i+1],Float.parseFloat(info[2+3*i+2])));
				}
				persons[counter].setAccounts(accs);
				counter++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File of Clients is not found!");
		}
		catch(IOException e) {
			System.out.println("File is empty!");
		}
	}
	
	@Override
	public String toString() {
		return "ATM [transactions=" + transactions + "]";
	}
	
	public Queue getTransactions() {
		return transactions;
	}

	public void setTransactions(Queue transactions) {
		this.transactions = transactions;
	}

	public static int checkInt() {
		// checks for the validity of the integer scanned, keeps asking for a positive number
		boolean is_Nb = false; int i = 0;
		do {
			try {
				i = Integer.parseInt(scan.nextLine());
				is_Nb= true;
				if(i<=0 || i>5) {
					System.out.println("Enter a positive number");
					is_Nb = false;
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}while(!is_Nb);
		return i;
		
	}
	
	public boolean welcome() {
		boolean found = false;
		String id_nb = null;
		String pass = null;
		while(!found) {
			System.out.println("\nWelcome to Theresa Bank!");
			try {
				System.out.println("Kindly enter your identification number");
				id_nb = scan.nextLine();
				found = true;
			} catch(NumberFormatException e) {
				System.out.println("Enter a number between 1 and 5: ");
				continue;
			}
			found = false;
			try {
				System.out.println("Kindly enter your password");
				pass = scan.nextLine();
				found = true;
			} catch(NumberFormatException e) {
				System.out.println("Enter a number: ");
				continue;
			}
		}
		found = validateIDPass(id_nb, pass);
		return found;
	}
	
	public static void displayAccountInfo(int i) {
		System.out.println("Kindly find below the balance for each account you have.");
		LinkedList accs = persons[i].getAccounts();
		for(int j=0; j<persons[index].getAccounts().size();j++) {
			System.out.println("Account Nb."+ ((Account)accs.get(j)).getAccount_number()+" Balance: " +((Account)accs.get(j)).getBalance());
		}
	}
	
	public static float checkAmountW(LinkedList accs, String index) {
		boolean found = false; int amount = 0;
		System.out.println("Enter the amount you wish to withdraw");
		do {
			try {
				amount = Integer.parseInt(scan.nextLine());
				found= true;
				if(amount<=0 || amount>((Account)accs.get(Integer.parseInt(index)-1)).getBalance()) {
					System.out.println("Choose a number between 1 and " +((Account)accs.get(Integer.parseInt(index)-1)).getBalance());
					found = false;
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}while(!found);
		return  amount;
	}

	public static float checkAmountD() {
		boolean found = false; int amount = 0;
		do {
			try {
				amount = Integer.parseInt(scan.nextLine());
				found= true;
				if(amount<=0 ) {
					System.out.println("Choose a number bigger than 0");
					found = false;
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}while(!found);
		return  amount;
	}
	
	public static String chooseAcc(LinkedList accnt) {
		boolean exists = false; int i = 0;
		do {
			try {
				i = Integer.parseInt(scan.nextLine());
				exists= true;
				if(i<=0 || i>accnt.size()) {
					System.out.println("Choose a number between 1 and " + accnt.size());
					exists = false;
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Enter a number");
			}
		}while(!exists);
		return ""+ i;
	}
	
	public static Transactions withdrawMoney(Transactions tr) {
		displayAccountInfo(index);
		LinkedList accounts= persons[index].getAccounts();
		if(accounts.size()==1)
			tr.setFrom_account_chosen("1");
		else {
			System.out.println("Enter the index of the account you wish to withdraw money from ");
			tr.setFrom_account_chosen(chooseAcc(accounts));
		}
		tr.setType("debit");
		tr.setAmount(checkAmountW(persons[index].getAccounts(), tr.getFrom_account_chosen()));
		return tr;
	}

	public static Transactions depositMoney(Transactions tr) {
		displayAccountInfo(index);
		LinkedList accounts= persons[index].getAccounts();
		if(accounts.size()==1)
			tr.setTo_account_chosen("1");
		else {
			System.out.println("Enter the index of the account you wish to deposit money to ");
			tr.setTo_account_chosen(chooseAcc(accounts));
		}
		tr.setType("credit");
		System.out.println("Enter the amount you wish to deposit");
		tr.setAmount(checkAmountD());
		return tr;
	}
	
	public static Transactions transferMoney(Transactions tr) {
		if(persons[index].getAccounts().size()<=1) {
			System.out.println("You don't have more than 1 account! You may not transfer money.");
			tr = null;
		}
		else {
			displayAccountInfo(index);
			LinkedList accounts= persons[index].getAccounts();
			System.out.println("Enter the  index of the account you wish to transfer money from ");
			tr.setFrom_account_chosen(chooseAcc(accounts));
			if(accounts.size()==2) {
				tr.setTo_account_chosen(""+ (3-Integer.parseInt(tr.getFrom_account_chosen())));
			}
			else{
				System.out.println("Enter the account number you wish to transfer money to ");
				do {
				System.out.println("Choose an account different from Acc ind." + tr.getFrom_account_chosen());
				tr.setTo_account_chosen(chooseAcc(accounts));
				} while(tr.getFrom_account_chosen().equals(tr.getTo_account_chosen()));
			}
			tr.setAmount(checkAmountW(persons[index].getAccounts(), tr.getFrom_account_chosen()));
		}
		return tr;
	}
	
	public static boolean checkTime(Transactions tr) {
		String in_date = tr.getDate();
		SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		Date dt1=null;
		try {
			dt1 = format1.parse(in_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// getting the day from a date
		String day=new SimpleDateFormat("EEEE").format(dt1);
		return (day!= "Sunday" && (tr.getTime().compareTo("18:00:00")< 0 && tr.getTime().compareTo("08:00:00")> 0));
	}
	
	
	public static void withdraw(Transactions tr, int ind) {
		float money = tr.getAmount();
		int account_ind = Integer.parseInt(tr.getFrom_account_chosen())-1;
		float balance = ((Account) persons[ind].getAccounts().get(account_ind)).getBalance();
		((Account) persons[ind].getAccounts().get(account_ind)).setBalance(balance - money);
	}
	

	public static void deposit(Transactions tr, int ind) {
		float money = tr.getAmount();
		int account_ind = Integer.parseInt(tr.getTo_account_chosen())-1;
		float balance = ((Account) persons[ind].getAccounts().get(account_ind)).getBalance();
		((Account) persons[ind].getAccounts().get(account_ind)).setBalance(balance + money);
	}
	
	
	public static void transfer(Transactions tr, int ind) {
		float money = tr.getAmount();
		int from = Integer.parseInt(tr.getFrom_account_chosen())-1;
		int to = Integer.parseInt(tr.getTo_account_chosen())-1;
		float from_balance = ((Account) persons[ind].getAccounts().get(from)).getBalance();
		float to_balance = ((Account) persons[ind].getAccounts().get(to)).getBalance();
		((Account) persons[ind].getAccounts().get(from)).setBalance(from_balance - money);
		((Account) persons[ind].getAccounts().get(to)).setBalance(to_balance + money);
	}
	
	
	public static void saveFile() {
		try {
			PrintWriter pw = new PrintWriter(new File("Database.txt"));
			for(int i=0; i<persons.length;i++) {
				pw.print(persons[i].getName()+","+persons[i].getClient_number());
				for(int j=0; j<persons[i].getAccounts().size();j++) {
					Account acc = (Account)persons[i].getAccounts().get(j);
					pw.print(","+acc.getAccount_number()+","+acc.getType()+","+acc.getBalance());
				}
				pw.print("\n");
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
	}
	
	
	public static int getIndex(Person pr) {
		int ind = -99;
		for(int i=0; i<persons.length;i++) {
			if(pr.getName().equals(persons[i].getName()) && pr.getClient_number().equals(persons[i].getClient_number())) {
				ind = i;
			}
		}
		return ind;
	}
	
	public void process() {
		int ind = -99;
		while(!transactions.empty()) {
			Transactions tr = (Transactions)transactions.dequeue();
			ind = getIndex(tr.getPerson());
			if(tr.getType().equals("debit")) {
				withdraw(tr, ind);
			}
			else if(tr.getType().equals("credit")) {
				deposit(tr,ind);
			}
			else {
				transfer(tr,ind);
			}
		}
	}
	
	public static void menu() {
		ATM atm = new ATM();
	
		while(true) {
			Date date = new Date();
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
			String time_frame = time.format(date);
			SimpleDateFormat day = new SimpleDateFormat("EEEE");
			String cur_day = day.format(date);
			 
			// The bank closes closes from 8:00 AM till 8:30 AM to process all previous transactions in a working day
			if(!atm.transactions.empty() && (time_frame.compareTo("08:00:00")>0 && time_frame.compareTo("08:30:00")<0) &&(!cur_day.equals("Sunday"))) {
				System.out.println("Ente hone");
				atm.process();
				saveFile();
			}
			
			boolean found = atm.welcome();
			if(!found) 
				continue;
			System.out.println("\nWelcome Ms/Mr "+ persons[index].getName()+"!");
			System.out.println("What a delightful visit!");
			boolean showMenu = true;
			Queue own_tr = new Queue();

			while(showMenu) {
				System.out.println("\n1.	Withdraw money");
				System.out.println("2.	Deposit money");
				System.out.println("3.	Transfer of Money");
				System.out.println("4.	Display account info");
				System.out.println("5.	Exit");
				System.out.println("- - - - - - - - - - - - - ");
				System.out.println("Input your choice");
				
				int in = checkInt(); 
				
				switch (in) {
				case 1:
					Transactions trw = new Transactions();
					trw.setPerson(persons[index]);
					trw = withdrawMoney(trw);
					atm.getTransactions().enqueue(trw);
					own_tr.enqueue(trw);
					if(checkTime(trw)) {
						withdraw((Transactions)atm.getTransactions().dequeue(), index);
						displayAccountInfo(index);
						own_tr.dequeue();
						System.out.println("Withdrawal successfully occurred!");
					}
					else {
						System.out.println("We will process the transaction in the following working day!");
					}
					continue;
				case 2:		
					Transactions trd = new Transactions();
					trd.setPerson(persons[index]);
					trd = depositMoney(trd);
					atm.getTransactions().enqueue(trd);
					own_tr.enqueue(trd);
					if(checkTime(trd)) {
						deposit((Transactions)atm.getTransactions().dequeue(),index);
						displayAccountInfo(index);
						own_tr.dequeue();
						System.out.println("Deposit successfully occurred!");
					}
					else {
						System.out.println("We will process the transaction in the following working day!");
					}
					continue;
				case 3:
					Transactions trt = new Transactions();
					trt.setPerson(persons[index]);
					trt = transferMoney(trt);
					if(trt!=null) {
						atm.getTransactions().enqueue(trt);
						own_tr.enqueue(trt);
						if(checkTime(trt)) {
							transfer((Transactions)atm.getTransactions().dequeue(),index);
							displayAccountInfo(index);
							own_tr.dequeue();
							System.out.println("Transfer successfully occurred!");
						}
						else {
							System.out.println("We will process the transaction in the following working day!");
						}
					}
					continue;
				case 4:
					displayAccountInfo(index);
					continue;
				case 5:
					saveFile();
					System.out.println("Thank you for using Theresa Bank! We wish you a good day!");
					if(!own_tr.empty()) {
						System.out.println("Kindly find below all the transactions that will be processed in the next working day!");
						while(!own_tr.empty()) {
							System.out.println(own_tr.dequeue());
						}
					}
					showMenu =false;
					break;
				}			
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		menu();
	}
	
	
}
