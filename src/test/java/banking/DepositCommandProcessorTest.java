package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DepositCommandProcessorTest {
    public static final String TEST_ID1 = "12345678";

    public static final String TEST_DEPOSIT_AMOUNT = "1000";

    DepositCommandProcessor depositCommandProcessor;
    Bank bank;

    private static class TestBank extends Bank {
        int lastId;
        double lastAmount;

        @Override
        public void deposit(int id, double amount) {
            this.lastId = id;
            this.lastAmount = amount;
        }
    }

    @Test
    void test_process_deposit_calls_bank_deposit_with_correct_arguments(){
        TestBank testBank = new TestBank();
        this.depositCommandProcessor = new DepositCommandProcessor(testBank);

        depositCommandProcessor.processDeposit(TEST_ID1,TEST_DEPOSIT_AMOUNT);

        Assertions.assertEquals(Integer.parseInt(TEST_ID1), testBank.lastId);
        Assertions.assertEquals(Double.parseDouble(TEST_DEPOSIT_AMOUNT), testBank.lastAmount);

    }

}
