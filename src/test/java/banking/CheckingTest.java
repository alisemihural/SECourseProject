package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckingTest {
	Checking checking;

	@BeforeEach
	public void setUp() {
		checking = new Checking(AccountTest.TEST_ID1, AccountTest.TEST_APR1);
	}

	@Test
	public void test_checking_starting_balance() {
		double actual = checking.getBalance();
		assertEquals(0.0, actual);
	}
}
