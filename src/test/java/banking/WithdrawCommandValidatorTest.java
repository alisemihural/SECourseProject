package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawCommandValidatorTest {
        WithdrawCommandValidator withdrawCommandValidator;
        PassTimeCommandProcessor passTimeCommandProcessor;

        Bank bank;

        private final Integer TEST_ID1 = 93339286;
        private final Double TEST_APR1 = 5d;

        @BeforeEach
        void setUp() {
            bank = new Bank();
            withdrawCommandValidator = new WithdrawCommandValidator(bank);
        }

        private boolean validateCommand(String command) {
            String[] parts = command.split(" ");
            String id = parts.length > 1 ? parts[1] : "";
            String withdrawAmount = parts.length > 2 ? parts[2] : "";
            return this.withdrawCommandValidator.validate(id, withdrawAmount, parts.length);
        }

        // VALID Withdrawals
        @Test
        void valid_Withdrawal_From_Checking_Account() {
            Integer TEST_ID1 = 63739286;
            Checking testCheckingAccount = new Checking(TEST_ID1,TEST_APR1);
            bank.addAccount(testCheckingAccount);

            Assertions.assertTrue(validateCommand("Withdraw 63739286 400"));
        }

        @Test
        void valid_Withdrawal_From_Savings_Account() {
            Integer TEST_ID1 = 93339286;
            Savings testSavingsAccount = new Savings(TEST_ID1,TEST_APR1);
            bank.addAccount(testSavingsAccount);

            Assertions.assertTrue(validateCommand("Withdraw 93339286 1000"));
        }

        @Test
        void invalidWithdrawalFromCDBefore12Months() {
            // Assuming a CD account with ID "11223344" which is less than 12 months old exists

            Integer TEST_ID1 = 11223344;
            Integer TEST_INITIAL_BALANCE = 1000;
            CD testCDAccount = new CD(TEST_ID1,TEST_APR1,TEST_INITIAL_BALANCE);
            bank.addAccount(testCDAccount);
            passTimeCommandProcessor.passTime(11);

            Assertions.assertFalse(validateCommand("Withdraw 11223344 1000"));
        }

        @Test
        void validFullWithdrawalFromCDAccountAfter12Months() {
            // Assuming a CD account with ID "44332211" which is more than 12 months old exists
            Integer TEST_ID1 = 44332211;
            Integer TEST_INITIAL_BALANCE = 1000;
            CD testCDAccount = new CD(TEST_ID1,TEST_APR1,TEST_INITIAL_BALANCE);
            bank.addAccount(testCDAccount);
            passTimeCommandProcessor.passTime(11);

            Assertions.assertTrue(validateCommand("Withdraw 44332211 [FULL BALANCE AMOUNT]"));
        }

        // VALID Case Insensitivity
        @Test
        void valid_case_insensitivity_in_withdraw() {
            Savings testSavingsAccount = new Savings(38912499,TEST_APR1);
            bank.addAccount(testSavingsAccount);
            boolean actual = validateCommand("withdraw 38912499 500");
            Assertions.assertTrue(actual);
        }


        // VALID ID
        @Test
        void valid_withdraw_command_with_valid_id() {
            Savings testSavingsAccount = new Savings(25489215,TEST_APR1);
            bank.addAccount(testSavingsAccount);
            boolean actual = validateCommand("Withdraw 25489215 100");
            Assertions.assertTrue(actual);
        }

        // VALID Withdraw Amount
        @Test
        void valid_withdraw_command_with_valid_withdraw_amount() {
            Savings testSavingsAccount = new Savings(35689217,TEST_APR1);
            bank.addAccount(testSavingsAccount);
            boolean actual = validateCommand("Withdraw 35689217 500");
            Assertions.assertTrue(actual);
        }

        @Test
        void valid_withdraw_command_with_withdraw_amount_of_zero() {
            Savings testSavingsAccount = new Savings(15339215,TEST_APR1);
            bank.addAccount(testSavingsAccount);
            boolean actual = validateCommand("Withdraw 15339215 0");
            Assertions.assertTrue(actual);
        }

        @Test
        void valid_withdraw_command_with_deposit_amount_with_decimal_points() {
            Savings testSavingsAccount = new Savings(88339289,TEST_APR1);
            bank.addAccount(testSavingsAccount);
            boolean actual = validateCommand("Withdraw 88339289 200.55");
            Assertions.assertTrue(actual);
        }

        @Test
        void valid_withdraw_command_with_deposit_amount_of_exactly_1000_for_Savings() {
            Savings testSavingsAccount = new Savings(93339286,TEST_APR1);
            bank.addAccount(testSavingsAccount);

            boolean actual = validateCommand("Withdraw 93339286 1000");
            Assertions.assertTrue(actual);
        }

        @Test
        void valid_withdraw_command_with_deposit_amount_of_exactly_400_for_Checking() {
            Integer TEST_ID2 = 63739286;
            Checking testCheckingAccount = new Checking(TEST_ID2,TEST_APR1);
            bank.addAccount(testCheckingAccount);

            boolean actual = validateCommand("Withdraw 63739286 400");
            Assertions.assertTrue(actual);
        }

        // INVALID Only Withdraw Command

        @Test
        void invalid_only_withdraw_command() {
            boolean actual = validateCommand("Withdraw");
            Assertions.assertFalse(actual);
        }

        // INVALID Too Many Arguments
        @Test
        void invalid_deposit_command_with_too_many_arguments() {
            boolean actual = validateCommand("Withdraw 253392836 100 800");
            Assertions.assertFalse(actual);
        }

        // INVALID ID Check
        @Test
        void invalid_deposit_command_with_non_integer_id() {
            boolean actual = validateCommand("Withdraw 123.456768 350");
            Assertions.assertFalse(actual);
        }

        @Test
        void invalid_deposit_command_with_too_long_id() {
            boolean actual = validateCommand("Withdraw 12339283676 100");
            Assertions.assertFalse(actual);
        }

        @Test
        void invalid_deposit_command_with_too_short_id() {
            boolean actual = validateCommand("Withdraw 123 100");
            Assertions.assertFalse(actual);
        }

        @Test
        void invalid_deposit_command_with_non_numeric_id() {
            boolean actual = validateCommand("Withdraw ABCDEFGH 350");
            Assertions.assertFalse(actual);
        }

        // INVALID Withdraw Amount Check
        @Test
        void invalid_withdraw_command_with_non_integer_deposit_amount() {
            boolean actual = validateCommand("Withdraw 733392865 ABCD");
            Assertions.assertFalse(actual);
        }

        @Test
        void invalid_withdraw_command_with_negative_deposit_amount() {
            boolean actual = validateCommand("Withdraw 933392861 -7000");
            Assertions.assertFalse(actual);
        }


        @Test
        void withdrawalFromNonExistentAccount() {
            Assertions.assertFalse(validateCommand("Withdraw 99999999 100")); // Non-existent account
        }

}




