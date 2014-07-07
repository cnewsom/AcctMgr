
public class CreditAccount extends Account
{
	private int creditLimit;
	private double apr;
	private boolean isOverLimit;
	public CreditAccount(String name, double balance, int creditLimit, double apr) {
		super(name, balance);
		setCreditLimit(creditLimit);
		setApr(apr);
		if (balance > creditLimit)
			isOverLimit = true;
		else
			isOverLimit = false;
	}
	public double getApr() {
		return apr;
	}
	public int getCreditLimit() {
		return creditLimit;
	}
	private void setApr(double apr) {
		this.apr = apr;
	}
	private void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	protected void addTransaction(Transaction t) {
		super.addTransaction(t);
		if (t.getTransactionType().equalsIgnoreCase("Charge"))
			balance = balance + t.getAmount();
		else
			balance = balance - t.getAmount();
		if (balance > creditLimit)
			setOverLimit(true);
		else
			setOverLimit(false);
	}
	private void setOverLimit(boolean isOverLimit) {
		this.isOverLimit = isOverLimit;
	}
	private boolean isOverLimit() {
		return isOverLimit;
	}
	public String toString() {
		String result = new String();
		if (isOverLimit())
			result += "Your account is over the credit limit";
		return "Name: " + name + "\nBalance: " + balance + "\nCredit Limit: " + creditLimit + "\nAPR: " + apr + result;
	}
}