
public class Transaction {
	private String description;
	private String date;
	private double amount;
	private String transactionType;
	public Transaction(String description, String date, double amount, String transactionType) {
		setDescription(description);
		setDate(date);
		setAmount(amount);
		setTransactionType(transactionType);
	}
	public String getDate() {
		return date;
	}
	public String getDescription() {
		return description;
	}
	public double getAmount() {
		return amount;
	}
	private void setDate(String date) {
		this.date = date;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	private void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	private void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}	
	public String toString() {
		return "Description: " + getDescription() + "\nDate: " + getDate()
				+ "\nAmount: " + getAmount() + "\nTransaction Type: " + getTransactionType();
	}
}