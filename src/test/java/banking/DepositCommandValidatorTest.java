package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositCommandValidatorTest {
    private DepositCommandValidator depositCommandValidator;
    private Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.depositCommandValidator = new DepositCommandValidator(bank);
    }

    private boolean validateCommand(String command) {
        String[] parts = command.split(" ");
        String id = parts.length > 1 ? parts[1] : "";
        String depositAmount = parts.length > 2 ? parts[2] : "";
        return this.depositCommandValidator.validate(id, depositAmount, parts.length);
    }

    // VALID Deposits
    @Test
    void valid_deposit_from_savings_account() {
        Integer TEST_ID1 = 12345678;
        Double TEST_APR1 = 5d;
        Savings testSavingsAccount = new Savings(TEST_ID1,TEST_APR1);
        bank.addAccount(testSavingsAccount);

        Assertions.assertTrue(validateCommand("Deposit 12345678 1000"));
    }


    @Test
    void valid_Deposit_From_Checking_Account() {
        Integer TEST_ID2 = 63739286;
        Double TEST_APR2 = 5d;

        Checking testCheckingAccount = new Checking(TEST_ID2,TEST_APR2);
        bank.addAccount(testCheckingAccount);

        Assertions.assertTrue(validateCommand("Deposit 63739286 400"));
    }

    @Test
    void valid_Deposit_From_CD_Account() {
        Integer TEST_ID3 = 23739216;
        Double TEST_APR3 = 5d;
        Double TEST_STARTING_BALANCE1 = 1500d;

        CD testCDAccount = new CD(TEST_ID3, TEST_APR3, TEST_STARTING_BALANCE1);
        bank.addAccount(testCDAccount);

        Assertions.assertTrue(validateCommand("Deposit 87654321 1000"));
    }

    // VALID Case Insensitivity
    @Test
    void valid_case_insensitivity_in_deposit() {
        boolean actual = validateCommand("deposit 38912499 500");
        Assertions.assertTrue(actual);
    }


    // VALID ID
    @Test
    void valid_deposit_command_with_valid_id() {
        boolean actual = validateCommand("Deposit 25489215 100");
        Assertions.assertTrue(actual);
    }

    // VALID Deposit Amount
    @Test
    void valid_deposit_command_with_valid_deposit_amount() {
        boolean actual = validateCommand("Deposit 35689217 500");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_deposit_command_with_deposit_amount_of_zero() {
        boolean actual = validateCommand("Deposit 15339215 0");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_deposit_command_with_deposit_amount_with_decimal_points() {
        boolean actual = validateCommand("Deposit 88339289 200.55");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_deposit_command_with_deposit_amount_of_exactly_2500_for_Savings() {
        Integer TEST_ID1 = 93339286;
        Double TEST_APR1 = 5d;
        Savings testSavingsAccount = new Savings(TEST_ID1,TEST_APR1);
        bank.addAccount(testSavingsAccount);

        boolean actual = validateCommand("Deposit 93339286 2500");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_deposit_command_with_deposit_amount_of_exactly_1000_for_Checking() {
        Integer TEST_ID2 = 63739286;
        Double TEST_APR2 = 5d;
        Checking testCheckingAccount = new Checking(TEST_ID2,TEST_APR2);
        bank.addAccount(testCheckingAccount);

        boolean actual = validateCommand("Deposit 63739286 1000");
        Assertions.assertTrue(actual);
    }

    // INVALID Only Deposit Command

    @Test
    void invalid_only_deposit_command() {
        boolean actual = validateCommand("Deposit");
        Assertions.assertFalse(actual);
    }


    // INVALID ID Check
    @Test
    void invalid_deposit_command_with_non_numeric_id() {
        boolean actual = validateCommand("Deposit 123.456768 350");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_too_long_id() {
        boolean actual = validateCommand("Deposit 12339283676 100");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_too_short_id() {
        boolean actual = validateCommand("Deposit 123 100");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_non_integer_id() {
        boolean actual = validateCommand("Deposit ABCDEFGH 350");
        Assertions.assertFalse(actual);
    }

    // INVALID Deposit Amount Check
    @Test
    void invalid_deposit_command_with_non_integer_deposit_amount() {
        boolean actual = validateCommand("Deposit 733392865 ABCD");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_negative_deposit_amount() {
        boolean actual = validateCommand("Deposit 933392861 -7000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_deposit_amount_of_more_than_2500_for_Savings() {
        Integer TEST_ID1 = 93339286;
        Double TEST_APR1 = 5d;
        Savings testSavingsAccount = new Savings(TEST_ID1,TEST_APR1);
        bank.addAccount(testSavingsAccount);

        boolean actual = validateCommand("Deposit 93339286 3000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_with_deposit_amount_of_more_than_1000_for_Checking() {
        Integer TEST_ID2 = 63739286;
        Double TEST_APR2 = 5d;
        Checking testCheckingAccount = new Checking(TEST_ID2,TEST_APR2);
        bank.addAccount(testCheckingAccount);

        boolean actual = validateCommand("Deposit 63739286 2000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_deposit_command_for_CD() {
        Integer TEST_ID3 = 23739216;
        Double TEST_APR3 = 5d;
        Double TEST_STARTING_BALANCE1 = 1500d;

        CD testCDAccount = new CD(TEST_ID3, TEST_APR3, TEST_STARTING_BALANCE1);
        bank.addAccount(testCDAccount);

        boolean actual = validateCommand("Deposit 23739216 2000");
        Assertions.assertFalse(actual);
    }
}
