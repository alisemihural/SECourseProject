package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CDTest {
	CD cd;

	@BeforeEach
	public void setUp() {
		cd = new CD(AccountTest.TEST_ID1, AccountTest.TEST_APR1, AccountTest.TEST_STARTING_BALANCE);
	}

	@Test
	public void test_cd_starting_balance() {
		double actual = cd.getBalance();
		assertEquals(AccountTest.TEST_STARTING_BALANCE, actual);
	}
}
