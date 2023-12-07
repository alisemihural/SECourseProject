package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsTest {
	Savings savings;

	@BeforeEach
	public void setUp() {
		savings = new Savings(AccountTest.TEST_ID1,AccountTest.TEST_APR1);
	}

	@Test
	public void test_savings_starting_balance() {
		double actual = savings.getBalance();
		assertEquals(0.0, actual);
	}
}
