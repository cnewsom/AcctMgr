import java.util.ArrayList;

public class AccountTest {

	public static void main(String[] args) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		CheckingAccount homeFed1 = new CheckingAccount("Home Federal Checking", -500);
		accounts.add(homeFed1);
		
		for (int i = 0; i < accounts.size(); i++)
			System.out.println(accounts.get(i));
		
		Transaction trans1 = new Transaction("Arby's", "6/5/13", 3.5, "withdrawal");
		System.out.println(homeFed1.transactions);
		homeFed1.transactions.add(trans1);
		System.out.println(homeFed1.transactions);
		for (int i = 0; i < homeFed1.transactions.size(); i++)
			System.out.println(homeFed1.transactions.get(i));
		
		
	}

}
