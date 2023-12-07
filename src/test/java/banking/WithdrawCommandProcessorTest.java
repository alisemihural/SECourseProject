package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandProcessorTest {
    public static final String TEST_ID1 = "12345678";

    public static final String TEST_WITHDRAW_AMOUNT = "1000";

    WithdrawCommandProcessor withdrawCommandProcessor;
    Bank bank;

    private static class TestBank extends Bank {
        int lastId;
        double lastAmount;

        @Override
        public void withdraw(int id, double amount) {
            this.lastId = id;
            this.lastAmount = amount;
        }
    }

    @Test
    void test_process_withdraw_calls_bank_withdraw_with_correct_arguments(){
        TestBank testBank = new TestBank();
        this.withdrawCommandProcessor = new WithdrawCommandProcessor(testBank);
        testBank.withdraw(Integer.parseInt(TEST_ID1),Double.parseDouble(TEST_WITHDRAW_AMOUNT));

        withdrawCommandProcessor.processWithdraw(TEST_ID1,TEST_WITHDRAW_AMOUNT);

        Assertions.assertEquals(Integer.parseInt(TEST_ID1), testBank.lastId);
        Assertions.assertEquals(Double.parseDouble(TEST_WITHDRAW_AMOUNT), testBank.lastAmount);

    }

    @Test
    void all_the_balance_is_withdrawn_from_CD(){
        TestBank testBank = new TestBank();
        this.withdrawCommandProcessor = new WithdrawCommandProcessor(testBank);
        testBank.withdraw(Integer.parseInt(TEST_ID1),Double.parseDouble(TEST_WITHDRAW_AMOUNT));

        withdrawCommandProcessor.processWithdraw(TEST_ID1,TEST_WITHDRAW_AMOUNT);

        Assertions.assertEquals(Integer.parseInt(TEST_ID1), testBank.lastId);
        Assertions.assertEquals(Double.parseDouble(TEST_WITHDRAW_AMOUNT), testBank.lastAmount);

    }

}
