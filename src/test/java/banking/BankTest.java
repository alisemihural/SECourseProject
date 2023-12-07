package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
	public static final Savings TEST_ACCOUNT1 = new Savings(AccountTest.TEST_ID1, AccountTest.TEST_APR1);
	public static final Checking TEST_ACCOUNT2 = new Checking(AccountTest.TEST_ID2, AccountTest.TEST_APR2);

	Bank bank;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
	}

	@Test
	public void bank_has_no_accounts_when_created() {
		assertTrue(bank.isEmpty());
	}

	@Test
	public void bank_has_one_account_in_it() {
		bank.addAccount(BankTest.TEST_ACCOUNT1);
		assertEquals(1, bank.numberOfAccounts());
	}

	@Test
	public void bank_has_two_account_in_it() {
		bank.addAccount(BankTest.TEST_ACCOUNT1);
		bank.addAccount(BankTest.TEST_ACCOUNT2);
		assertEquals(2, bank.numberOfAccounts());
	}

	@Test
	public void retrieve_the_correct_account() {
		bank.addAccount(BankTest.TEST_ACCOUNT1);
		Account account = bank.retrieve(AccountTest.TEST_ID1);
		assertEquals(AccountTest.TEST_ID1, account.getID());
	}

	@Test
	public void deposit_to_the_correct_account() {
		bank.addAccount(BankTest.TEST_ACCOUNT1);
		Account account = bank.retrieve(AccountTest.TEST_ID1);

		double oldBalance = account.getBalance();
		bank.deposit(AccountTest.TEST_ID1, AccountTest.TEST_DEPOSIT_AMOUNT);
		double currentBalance = account.getBalance();

		assertEquals(oldBalance + AccountTest.TEST_DEPOSIT_AMOUNT, currentBalance);
	}

	@Test
	public void withdraw_from_the_correct_account() {
		bank.addAccount(BankTest.TEST_ACCOUNT1);
		Account account = bank.retrieve(AccountTest.TEST_ID1);
		account.deposit(AccountTest.TEST_DEPOSIT_AMOUNT);

		double oldBalance = account.getBalance();
		bank.withdraw(AccountTest.TEST_ID1, AccountTest.TEST_WITHDRAW_AMOUNT);
		double currentBalance = account.getBalance();
		double expected = oldBalance - AccountTest.TEST_WITHDRAW_AMOUNT;

		assertEquals(expected, currentBalance);
	}

	@Test
	public void deposit_to_the_correct_account_twice() {
		bank.addAccount(BankTest.TEST_ACCOUNT1);
		Account account = bank.retrieve(AccountTest.TEST_ID1);

		double oldBalance = account.getBalance();
		bank.deposit(AccountTest.TEST_ID1, AccountTest.TEST_DEPOSIT_AMOUNT);
		bank.deposit(AccountTest.TEST_ID1, AccountTest.TEST_DEPOSIT_AMOUNT);
		double currentBalance = account.getBalance();

		assertEquals(oldBalance + AccountTest.TEST_DEPOSIT_AMOUNT + AccountTest.TEST_DEPOSIT_AMOUNT, currentBalance);
	}

	@Test
	public void withdraw_from_the_correct_account_twice() {
		bank.addAccount(BankTest.TEST_ACCOUNT1);
		Account account = bank.retrieve(AccountTest.TEST_ID1);
		account.deposit(AccountTest.TEST_DEPOSIT_AMOUNT);

		double oldBalance = account.getBalance();
		bank.withdraw(AccountTest.TEST_ID1, AccountTest.TEST_WITHDRAW_AMOUNT);
		bank.withdraw(AccountTest.TEST_ID1, AccountTest.TEST_WITHDRAW_AMOUNT);
		double currentBalance = account.getBalance();
		double expected = oldBalance - AccountTest.TEST_WITHDRAW_AMOUNT - AccountTest.TEST_WITHDRAW_AMOUNT;

		assertEquals(expected, currentBalance);
	}
}
