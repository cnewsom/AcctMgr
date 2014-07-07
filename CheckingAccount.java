
public class CheckingAccount extends Account {
	private boolean isOverdrawn;
	public CheckingAccount(String name, double balance) {
		super(name, balance);
		if (balance < 0)
			isOverdrawn = true;
		else
			isOverdrawn = false;
	}
	private boolean isOverdrawn() {
		return isOverdrawn;
	}
	private void setOverdrawn(boolean isOverdrawn) {
		this.isOverdrawn = isOverdrawn;
	}
	protected void addTransaction(Transaction t) {
		super.addTransaction(t);
		if (t.getTransactionType().equalsIgnoreCase("Withdrawal"))
			balance = balance - t.getAmount();
		else
			balance = balance + t.getAmount();
		
		if (balance < 0)
			setOverdrawn(true);
		else
			setOverdrawn(false);
	}
	public String toString() {
		String result = new String();
		if (isOverdrawn())
			result += "Your account is overdrawn";
		return "Name: " + name + "\nBalance: " + balance + "\n" + result;
	}
}