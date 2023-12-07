package banking;

import java.util.Collection;
import java.util.HashMap;

public class Bank {
	private HashMap<Integer, Account> bankDB = new HashMap<>();

	public Collection<Account> getBankDB(){
		return bankDB.values();
	}

	public boolean isEmpty() {
		return bankDB.isEmpty();
	}

	public int numberOfAccounts() {
		return bankDB.size();
	}

	public void addAccount(Account account) {
		bankDB.put(account.getID(), account);
	}

	public Account retrieve(int id) {
		return bankDB.get(id);
	}

	public void deposit(int id, double amount) {
		Account account = retrieve(id);
		if (account != null) {
			account.deposit(amount);
		}
	}

	public void withdraw(int id, double amount) {
		Account account = retrieve(id);
		if (account != null) {
			account.withdraw(amount);
		}
	}

	public void deleteAccount(Account account){
		bankDB.remove(account.getID());
	}
}
