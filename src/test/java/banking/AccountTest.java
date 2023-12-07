package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	public static final int TEST_ID1 = 12345678;
	public static final int TEST_ID2 = 87654321;
	public static final int TEST_ID3 = 18273645;
	public static final double TEST_APR1 = 10.0;
	public static final double TEST_APR2 = 5.2;
	public static final double TEST_APR3 = 3.2;
	public static final double TEST_STARTING_BALANCE = 50;
	public static final double TEST_DEPOSIT_AMOUNT = 200;
	public static final double TEST_WITHDRAW_AMOUNT = 100;

	Account account;

	@BeforeEach
	public void setUp() {
		account = new Savings(AccountTest.TEST_ID1, AccountTest.TEST_APR1);
	}

	@Test
	public void test_apr() {
		double actual = account.getApr();
		assertEquals(AccountTest.TEST_APR1, actual);
	}

	@Test
	public void balance_increase_when_deposit() {
		double oldBalance = account.getBalance();
		account.deposit(AccountTest.TEST_DEPOSIT_AMOUNT);
		assertEquals(oldBalance + AccountTest.TEST_DEPOSIT_AMOUNT, account.getBalance());
	}

	@Test
	public void balance_decrease_when_withdraw() {
		account.deposit(TEST_DEPOSIT_AMOUNT);
		double oldBalance = account.getBalance();
		account.withdraw(AccountTest.TEST_WITHDRAW_AMOUNT);
		double currentBalance = account.getBalance();
		double expected = oldBalance - AccountTest.TEST_WITHDRAW_AMOUNT;

		assertEquals(expected, currentBalance);

	}

	@Test
	public void balance_cant_go_below_zero() {
		double balance = account.getBalance();
		account.withdraw(balance + 1);
		assertEquals(0, account.getBalance());
	}

	@Test
	public void balance_increase_when_deposit_twice() {
		double oldBalance = account.getBalance();
		account.deposit(AccountTest.TEST_DEPOSIT_AMOUNT);
		account.deposit(AccountTest.TEST_DEPOSIT_AMOUNT);
		assertEquals(oldBalance + AccountTest.TEST_DEPOSIT_AMOUNT + AccountTest.TEST_DEPOSIT_AMOUNT, account.getBalance());
	}

	@Test
	public void balance_decrease_when_withdraw_twice() {
		account.deposit(TEST_DEPOSIT_AMOUNT);
		double oldBalance = account.getBalance();
		account.withdraw(AccountTest.TEST_WITHDRAW_AMOUNT);
		account.withdraw(AccountTest.TEST_WITHDRAW_AMOUNT);
		double currentBalance = account.getBalance();
		double expected = oldBalance - AccountTest.TEST_WITHDRAW_AMOUNT - AccountTest.TEST_WITHDRAW_AMOUNT;

		assertEquals(expected, currentBalance);
	}

}
