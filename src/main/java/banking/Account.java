package banking;

public abstract class Account {

	public double balance;
	private double apr;
	private int id;
	private int accountAge;
	private int monthlyWithdrawCount;

	public Account(int id, double apr) {
		this.id = id;
		this.apr = apr;
		this.balance = 0;
		this.accountAge = 0;
		this.monthlyWithdrawCount = 0;
	}

	public int getID() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public double getApr() {
		return apr;
	}

	public int getMonthlyWithdrawCount(){
		return monthlyWithdrawCount;
	}

	public int getAccountAge(){
		return accountAge;
	}

	public void increaseAccountAge(){
		accountAge++;
	}

	public void increaseMonthlyWithdrawCount(){
		monthlyWithdrawCount++;
	}

	public void resetMonthlyWithdrawCount(){
		monthlyWithdrawCount = 0;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		balance -= amount;
		if (balance < 0.0) {
			balance = 0.0;
		}
	}

}
