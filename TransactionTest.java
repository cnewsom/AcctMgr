import java.util.ArrayList;

public class TransactionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		Transaction trans1 = new Transaction("Arby's", "5/26/2013", 3.5, "withdrawal");
		Transaction trans2 = new Transaction("Chevron", "5/25/2013", 50, "withdrawal");
		transactions.add(trans2); transactions.add(trans1);
		for (int i = 0; i < transactions.size(); i++)
		System.out.println(transactions.get(i));

	}

}
