package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeCommandProcessorTest {
    private static final int TEST_ID = 12345678;
    private static final double TEST_APR = 2.1d;
    PassTimeCommandProcessor passTimeCommandProcessor;
    Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.passTimeCommandProcessor = new PassTimeCommandProcessor(bank);
    }

    // Pass Time Behaviour Tests
    @Test
    void account_with_a_balance_of_zero_is_closed_at_the_end_of_the_month(){
        Savings testSavingsAccount = new Savings(TEST_ID,TEST_APR);
        bank.addAccount(testSavingsAccount);

        passTimeCommandProcessor.passTime(1);

        Assertions.assertNull(bank.retrieve(TEST_ID));
    }

    @Test
    void account_with_a_balance_less_than_100_got_deducted_25(){
        Savings testSavingsAccount = new Savings(TEST_ID,TEST_APR);
        bank.addAccount(testSavingsAccount);
        bank.deposit(TEST_ID,90);

        double decimalAPR = testSavingsAccount.getApr() / 100;
        double monthlyAPR = decimalAPR / 12;
        double interestAmount = (testSavingsAccount.getBalance() - 25) * monthlyAPR;
        double actual = interestAmount + (testSavingsAccount.getBalance() - 25);

        passTimeCommandProcessor.passTime(1);

        Assertions.assertEquals(bank.retrieve(TEST_ID).getBalance(),actual);
    }

    @Test
    void account_with_a_balance_exactly_100_not_deducted_25(){
        Savings testSavingsAccount = new Savings(TEST_ID,TEST_APR);
        bank.addAccount(testSavingsAccount);
        bank.deposit(TEST_ID,100);

        double decimalAPR = testSavingsAccount.getApr() / 100;
        double monthlyAPR = decimalAPR / 12;
        double interestAmount = testSavingsAccount.getBalance() * monthlyAPR;
        double actual = interestAmount + testSavingsAccount.getBalance();

        passTimeCommandProcessor.passTime(1);

        Assertions.assertEquals(bank.retrieve(TEST_ID).getBalance(),actual);
    }

    @Test
    void savings_account_monthly_balance_update(){
        Savings testSavingsAccount = new Savings(TEST_ID,TEST_APR);
        bank.addAccount(testSavingsAccount);
        bank.deposit(TEST_ID,5000);

        double decimalAPR = testSavingsAccount.getApr() / 100;
        double monthlyAPR = decimalAPR / 12;
        double interestAmount = testSavingsAccount.getBalance() * monthlyAPR;
        double actual = interestAmount + testSavingsAccount.getBalance();

        passTimeCommandProcessor.passTime(1);

        Assertions.assertEquals(bank.retrieve(TEST_ID).getBalance(),5008.75);
    }

    @Test
    void checking_account_monthly_apr_calculation(){
        Checking testCheckingAccount = new Checking(TEST_ID,TEST_APR);
        bank.addAccount(testCheckingAccount);
        bank.deposit(TEST_ID,5000);

        double decimalAPR = testCheckingAccount.getApr() / 100;
        double monthlyAPR = decimalAPR / 12;
        double interestAmount = testCheckingAccount.getBalance() * monthlyAPR;
        double actual = interestAmount + testCheckingAccount.getBalance();

        passTimeCommandProcessor.passTime(1);

        Assertions.assertEquals(bank.retrieve(TEST_ID).getBalance(),actual);
    }

    @Test
    void CD_account_monthly_apr_calculation(){
        CD testCDAccount = new CD(TEST_ID,TEST_APR,2000);
        bank.addAccount(testCDAccount);


        double actual = testCDAccount.getBalance();
        double decimalAPR = testCDAccount.getApr() / 100;
        double monthlyAPR = decimalAPR / 12;
        for (int j =0; j<4; j++){
            double interestAmount = actual * monthlyAPR;

            actual =  actual + interestAmount;
        }

        passTimeCommandProcessor.passTime(1);

        Assertions.assertEquals(bank.retrieve(TEST_ID).getBalance(),actual);
    }

    @Test
    void passTime_increases_the_account_age(){

    }

    @Test
    void passTime_resets_monthly_withdrawal_count(){

    }


}
