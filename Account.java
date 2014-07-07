import java.util.ArrayList;
abstract class Account {
	protected double balance;
	protected String name;
	protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	protected Transaction[] trans;
	public Account(String name, double balance) {
		setName(name);
		setBalance(balance);
	}
	protected String getName() {
		return name;
	}
	protected double getBalance() {
		return balance;
	}
	private void setBalance(double balance) {
		this.balance = balance;
	}
	private void setName(String name) {
		this.name = name;
	}
	protected void addTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	private Transaction[] arrayListToArray(ArrayList<Transaction> transactions) {
		trans = new Transaction[transactions.size()]; 
		for (int i = 0; i < transactions.size(); i++)
			trans[i] = transactions.get(i);
		return trans;
	}
	public Transaction[] getTransactionsArray() {
		trans = arrayListToArray(transactions);
		return trans;
	}
	public ArrayList<Transaction> getTransactionsArrayList() {
		return transactions;
	}
}