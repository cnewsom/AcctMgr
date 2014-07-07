public class SavingsAccount extends Account {
	private double apr;
	public SavingsAccount(String name, double balance, double apr) {
		super(name, balance);
		setApr(apr);
	}
	public double getApr() {
		return apr;
	}
	private void setApr(double apr) {
		this.apr = apr;
	}
	@Override
	protected void addTransaction(Transaction t) {
		super.addTransaction(t);
		if (t.getTransactionType().equalsIgnoreCase("deposit"))
			balance = balance + t.getAmount();
		else
			balance = balance - t.getAmount();
	}
	@Override
	public String toString() {
		return "Name: " + name + "\nBalance: " + balance + "\nAPR " + apr;
	}
}