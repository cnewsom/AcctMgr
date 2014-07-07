import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
/** The CLI application used to maintain an electronic register to keep track of an ArrayList of Accounts. 
 */
public class AccountManager {
	
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	private static ArrayList<CheckingAccount> chkaccts = new ArrayList<CheckingAccount>();
	private static ArrayList<SavingsAccount> savaccts = new ArrayList<SavingsAccount>();
	private static ArrayList<CreditAccount> credaccts = new ArrayList<CreditAccount>();
	/**  
	 * @param args - The file name to be written to.
	 */
	public static void main(String[] args) {
		File myFile = new File("register.txt"); // the File object
		File myTempFile = new File("temp_register.txt");
		PrintWriter toFile = null; // PrintWriter object to write to the file.
		PrintWriter overWriteFile = null;
		FileWriter fwAppend = null; // FileWriter object to pass in to our PrintWriter in order to append Transactions.
		FileWriter fwOverWrite = null;
		Scanner keyboard = new Scanner(System.in); // input stream from the keyboard.
		Scanner readFile = null; // Scanner to read the file to create our ArrayList of accounts, transactions, etc.
		ArrayList<String> strings = new ArrayList<String>(); // the lines of the file.
		CheckingAccount checking; 
		SavingsAccount savings; // An object of each type of account.
		CreditAccount credit;
		
		try {
			fwAppend = new FileWriter(myFile, true); // the FileWriter
			fwOverWrite = new FileWriter(myTempFile, true); // If we need to change the contents of the file.
			toFile = new PrintWriter(fwAppend); // the PrintWriter for the main file
			overWriteFile = new PrintWriter(fwOverWrite); // the PrintWriter for the temp file.
			readFile = new Scanner(myFile); // the Scanner to read the lines of the file.
		}
		
		catch (FileNotFoundException e) {
			System.err.println("Error accessing file " + myFile); // make sure that the file can be accessed/created.
			System.err.println(e.getMessage());
		}
		
		catch (IOException e) { // make sure no IOException occurs with FileWriter
			e.printStackTrace();
			System.exit(-1);
		}
		
		String accountMenuChoice, transactionMenuChoice; // user options
		int index = 0; 
		while (readFile.hasNextLine()) { // read the file
			String nextLine = readFile.nextLine();
			strings.add(nextLine);
		}
		
		String[] result = new String[5];
		Transaction t = null;
		if (strings.size() != 1) {
			
			for (int i = 0; i < strings.size(); i++) {
				result = strings.get(i).split("\\s");
		
				if (result[0].equalsIgnoreCase("Checking")) {
					checking = new CheckingAccount(result[1], Double.valueOf(result[2]));
					chkaccts.add(checking);
					accounts.add(checking);
				}
			
				else if (result[0].equalsIgnoreCase("Savings")) {
					savings = new SavingsAccount(result[1], Double.valueOf(result[2]), Double.valueOf(result[3]));
					savaccts.add(savings);
					accounts.add(savings);
				}
			
				else if (result[0].equalsIgnoreCase("Credit")) {
					credit = new CreditAccount(result[1], Double.valueOf(result[2]), Integer.valueOf(result[3]), Double.valueOf(result[4]));
					credaccts.add(credit);
					accounts.add(credit);
				}
	
				else {
					System.out.println(result[0]);
					System.out.println(result.length);
					t = new Transaction(result[0], result[1], Double.valueOf(result[2]), result[3]);
					accounts.get(accounts.size()-1).addTransaction(t);
				}
			}
		}
		
			do {
				System.out.println("Account Manager"); //The main menu of the application
				System.out.println();
				System.out.println("What would you like to do?");
				System.out.println();
				System.out.println("1. View Accounts.");
				System.out.println("2. Add an account.");
				System.out.println("3. Delete an account.");
				System.out.println("4. I'm done for now.");
				
				accountMenuChoice = keyboard.nextLine(); // the user's choice
				String accountName, accountType, apr = null, balance, userInput;
				
				switch (Integer.valueOf(accountMenuChoice)) {
				case 1:
					if (accounts.size() != 0) { // Only display if there are accounts
					System.out.println("Which account would you like to view?");
					System.out.println();
					for (int i = 0; i < accounts.size(); i++)
						System.out.println(accounts.get(i));
					userInput = keyboard.nextLine().replace(' ', '_'); // The user's choice
					for (int i = 0; i < accounts.size(); i++)
						if (accounts.get(i).getName().equalsIgnoreCase(userInput)) { 	   	// check to see if the name of the account
							System.out.println(accounts.get(i).getName().replace('_', ' ')); // the user entered matches any of the accounts.
							index = i;
						}
						else 
							System.out.println("There aren't any accounts with that name");
					}
					else
						System.out.println("No accounts have been added.");
					System.out.println(index);
					if (accounts.size() != 0) {
					System.out.println("What would you like to do?");
					System.out.println();
					System.out.println("1. View Transactions.");
					System.out.println("2. Add a transaction.");
					System.out.println("3. Delete a transaction.");
					System.out.println("4. Go back to accounts.");
					transactionMenuChoice = keyboard.nextLine();
					
					String description, date, amount, transactionType;
					double transactionAmount;
					switch (Integer.valueOf(transactionMenuChoice)) {
					case 1:
						System.out.println(accounts.get(index).getTransactionsArray().length);
						Transaction[] transactions = new Transaction[accounts.get(index).getTransactionsArray().length];
						transactions = accounts.get(index).getTransactionsArray().clone();
						for (int i = 0; i < transactions.length; i++)
							System.out.println(transactions[i]);
						if (transactions.length != 0) {
						for (int i = 0; i < transactions.length; i++)
							transactions[i] = accounts.get(index).getTransactionsArray()[i];
						for (int j = 0; j < transactions.length; j++)
							System.out.println((j + 1) + ". " + transactions[j]);
						}
						else
							System.out.println("No transactions have been posted to this account.");
						break;
					case 2:
						System.out.println("Please enter a description of the transaction.");
						description = keyboard.nextLine();
						System.out.println("Please enter the date of the transaction (e.g 01/01/15).");
						date = keyboard.nextLine();
						System.out.println("Please enter the amount of the transaction.");
						amount = keyboard.nextLine();
						transactionAmount = Double.valueOf(amount);
						System.out.println("Please enter the transaction type.");
						transactionType = keyboard.nextLine();
						t = new Transaction(description, date, transactionAmount, transactionType);
						accounts.get(index).addTransaction(t);
						toFile.println(description + " " + date + " " + amount + " " + transactionType);
						break;
					case 3:
						ArrayList<Transaction> trans = new ArrayList<Transaction>();
						trans = accounts.get(index).getTransactionsArrayList();
						System.out.println("Which transaction would you like to delete?");
						System.out.println();
						for (int i = 0; i < accounts.get(index).getTransactionsArrayList().size(); i++) {
							System.out.print((i + 1) + ". ");
							System.out.print(accounts.get(index).getTransactionsArrayList().get(i).getDescription());
							System.out.print(" " + accounts.get(index).getTransactionsArrayList().get(i).getDate());
							System.out.print(" " + accounts.get(index).getTransactionsArrayList().get(i).getAmount());
							System.out.println(" " + accounts.get(index).getTransactionsArrayList().get(i).getTransactionType());
						}
						int i = Integer.valueOf(keyboard.nextLine());
						trans.remove(i-1);
						break;
					}
					}
					break;
				case 2:
					String creditLimit;
					System.out.print("Please enter the name of the account. ");
					accountName = keyboard.nextLine().replace(' ','_');
					System.out.print("What type of account is it? Checking, Savings, or Credit? ");
					accountType = keyboard.nextLine();
					System.out.print("What is the balance on the account. ");
					balance = keyboard.nextLine();
					if (accountType.equalsIgnoreCase("Checking")) {
						checking = new CheckingAccount(accountName, Double.valueOf(balance));
						accounts.add(checking);
						chkaccts.add(checking);
						toFile.println("Checking " + accountName + " " + balance);
					}
					else if (accountType.equalsIgnoreCase("Savings")) {
						System.out.println("What is the Annual Percentage Rate (APR)? ");
					    apr = keyboard.nextLine();
					    savings = new SavingsAccount(accountName, Double.valueOf(balance), Double.valueOf(apr));
					    accounts.add(savings);
					    savaccts.add(savings);
					    toFile.println("Savings " + accountName + " " + balance + " " + apr);
					}
					else if (accountType.equalsIgnoreCase("Credit")) {
						System.out.println("What is the credit limit?");
						creditLimit = keyboard.nextLine();
						System.out.println("What is the Annual Percentage Rate (APR)? ");
						apr = keyboard.nextLine();
						credit = new CreditAccount(accountName, Double.valueOf(balance), Integer.valueOf(creditLimit), Double.valueOf(apr));
						accounts.add(credit);
						credaccts.add(credit);
						toFile.println("Credit " + accountName + " " + balance + " " + creditLimit + " " + apr);		
					}						
					break;
				case 3:
					System.out.println("Which account would you like to delete?");
					for (int i = 0; i < accounts.size(); i++)
						System.out.println(accounts.get(i));
					accountName = keyboard.nextLine();
					for (int i = 0; i < accounts.size(); i++)
						if (accounts.get(i).getName().equalsIgnoreCase(accountName))
					accounts.remove(i);
					break;
				case 4:
					System.out.println("Goodbye!");
					break;
				}
			}
			while (Integer.valueOf(accountMenuChoice) != 4 );
			
			for (int chkIndex = 0; chkIndex < chkaccts.size(); chkIndex++) {
				overWriteFile.println("Checking " + chkaccts.get(chkIndex).getName() + " " + chkaccts.get(chkIndex).getBalance());
				Transaction[] trans = chkaccts.get(chkIndex).getTransactionsArray().clone();
				for (int transIndex = 0; transIndex < chkaccts.get(chkIndex).getTransactionsArray().length; transIndex++) {
					overWriteFile.println(trans[transIndex].getDescription() + " " + trans[transIndex].getDate() + " "
							+ trans[transIndex].getAmount() + " " + trans[transIndex].getTransactionType());
				}
			}	
			for (int savIndex = 0; savIndex < savaccts.size(); savIndex++) {
				overWriteFile.println("Savings " + savaccts.get(savIndex).getName() + " " + savaccts.get(savIndex).getBalance() 
							+ " " + savaccts.get(savIndex).getApr());
				Transaction[] trans = savaccts.get(savIndex).getTransactionsArray().clone();
				for (int transIndex = 0; transIndex < chkaccts.get(savIndex).getTransactionsArray().length; transIndex++) {
					overWriteFile.println(trans[transIndex].getDescription() + " " + trans[transIndex].getDate() + " "
							+ trans[transIndex].getAmount() + " " + trans[transIndex].getTransactionType());
				}
			}			
			for (int credIndex = 0; credIndex < credaccts.size(); credIndex++) {
				overWriteFile.println("Credit " +credaccts.get(credIndex).getName() + " " + credaccts.get(credIndex).getBalance() 
							+ " " + credaccts.get(credIndex).getCreditLimit() + " " + credaccts.get(credIndex).getApr());
				Transaction[] trans = credaccts.get(credIndex).getTransactionsArray().clone();
				for (int transIndex = 0; transIndex < credaccts.get(credIndex).getTransactionsArray().length; transIndex++) {
					overWriteFile.println(trans[transIndex].getDescription() + " " + trans[transIndex].getDate() + " "
							+ trans[transIndex].getAmount() + " " + trans[transIndex].getTransactionType());
				}
			}			
			overWriteFile.close();
			toFile.close();
			myFile.delete();
			myTempFile.renameTo(myFile);
	}
}