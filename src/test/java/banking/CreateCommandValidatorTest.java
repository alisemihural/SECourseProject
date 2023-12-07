package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandValidatorTest {
    ParentCommandValidator parentCommandValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.parentCommandValidator = new ParentCommandValidator(bank);
    }

    @Test
    void valid_case_insensitivity_in_create(){
        boolean actual = this.parentCommandValidator.validate("create savings 18912888 0.1");
        Assertions.assertTrue(actual);
    }

    @Test
    void invalid_typo_in_create(){
        boolean actual = this.parentCommandValidator.validate("Creeaate savings 55512899 0.3");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_missing_create(){
        boolean actual = this.parentCommandValidator.validate("savings 11112899 0.5");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_missing_account_type(){
        boolean actual = this.parentCommandValidator.validate("create 33312899 0.7");
        Assertions.assertFalse(actual);
    }

    //////////// banking.Savings Tests ////////////
    @Test
    void savings_valid_create_command_with_valid_id(){
        boolean actual = this.parentCommandValidator.validate("Create savings 25689215 0.8");
        Assertions.assertTrue(actual);
    }

    @Test
    void savings_valid_create_command_with_apr_value_with_two_decimals(){
        boolean actual = this.parentCommandValidator.validate("Create savings 45678912 0.32");
        Assertions.assertTrue(actual);
    }

    @Test
    void savings_valid_create_command_with_apr_value_of_zero(){
        boolean actual = this.parentCommandValidator.validate("Create savings 55677215 0");
        Assertions.assertTrue(actual);
    }

    @Test
    void savings_invalid_create_command_with_too_many_arguments(){
        boolean actual = this.parentCommandValidator.validate("Create savings 55689215 0.4 12345");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_non_numeric_id(){
        boolean actual = this.parentCommandValidator.validate("Create savings ABCDEFGH 0.1");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_too_long_id(){
        boolean actual = this.parentCommandValidator.validate("Create savings 590829034 0.6");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_too_short_id(){
        boolean actual = this.parentCommandValidator.validate("Create savings 123421 0.7");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_non_integer_id(){
        boolean actual = this.parentCommandValidator.validate("Create savings 52345.673 0.3");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_duplicate_account_creation(){
        Savings newAccount = new Savings(55689215,AccountTest.TEST_APR1);
        bank.addAccount(newAccount);
        boolean actual = this.parentCommandValidator.validate("Create savings 55689215 0.4");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_non_numeric_apr(){
        boolean actual = this.parentCommandValidator.validate("Create savings 78987923 AB.banking.CD");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_negative_apr(){
        boolean actual = this.parentCommandValidator.validate("Create savings 58912899 -0.4");
        Assertions.assertFalse(actual);
    }

    @Test
    void savings_invalid_create_command_with_bigger_than_ten_apr(){
        boolean actual = this.parentCommandValidator.validate("Create savings 58912899 11.2");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_savings_missing_id(){
        boolean actual = this.parentCommandValidator.validate("Create savings 8.2");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_savings_missing_apr(){
        boolean actual = this.parentCommandValidator.validate("Create savings 38912829");
        Assertions.assertFalse(actual);
    }

    //////////// banking.Checking Tests ////////////
    @Test
    void checking_valid_create_command_with_valid_id(){
        boolean actual = this.parentCommandValidator.validate("Create checking 65909214 0.2");
        Assertions.assertTrue(actual);
    }

    @Test
    void checking_valid_create_command_with_apr_value_with_two_decimals(){
        boolean actual = this.parentCommandValidator.validate("Create checking 45678912 0.52");
        Assertions.assertTrue(actual);
    }

    @Test
    void checking_valid_create_command_with_apr_value_of_zero(){
        boolean actual = this.parentCommandValidator.validate("Create checking 92386515 0");
        Assertions.assertTrue(actual);
    }

    @Test
    void checking_invalid_create_command_with_too_many_arguments(){
        boolean actual = this.parentCommandValidator.validate("Create checking 09289215 0.1 12938");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_non_numeric_id(){
        boolean actual = this.parentCommandValidator.validate("Create checking HJKLMNPO 0.2");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_too_long_id(){
        boolean actual = this.parentCommandValidator.validate("Create checking 590829034 0.3");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_too_short_id(){
        boolean actual = this.parentCommandValidator.validate("Create checking 190421 0.9");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_non_integer_id(){
        boolean actual = this.parentCommandValidator.validate("Create checking 35945.672 0.4");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_duplicate_account_creation(){
        Checking newAccount = new Checking(55689215,AccountTest.TEST_APR1);
        bank.addAccount(newAccount);
        boolean actual = this.parentCommandValidator.validate("Create checking 55689215 0.1");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_non_numeric_apr(){
        boolean actual = this.parentCommandValidator.validate("Create checking 78987923 GK.banking.CD");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_negative_apr(){
        boolean actual = this.parentCommandValidator.validate("Create checking 38966821 -0.2");
        Assertions.assertFalse(actual);
    }

    @Test
    void checking_invalid_create_command_with_bigger_than_ten_apr(){
        boolean actual = this.parentCommandValidator.validate("Create checking 18912890 11.2");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_checking_missing_id(){
        boolean actual = this.parentCommandValidator.validate("Create checking 8.2");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_checking_missing_apr(){
        boolean actual = this.parentCommandValidator.validate("Create checking 33912829");
        Assertions.assertFalse(actual);
    }

    //////////// banking.CD Tests ////////////
    @Test
    void CD_valid_create_command_with_valid_id(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 15689213 0.8 1500");
        Assertions.assertTrue(actual);
    }

    @Test
    void CD_valid_create_command_with_apr_value_with_two_decimals(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 45678912 0.95 2000");
        Assertions.assertTrue(actual);
    }

    @Test
    void CD_valid_create_command_with_apr_value_of_zero(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 35668213 0 2500");
        Assertions.assertTrue(actual);
    }

    @Test
    void CD_invalid_create_command_with_too_many_arguments(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 65639015 0.1 4000 12938");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_non_numeric_id(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD FGUIABCD 0.5 5000");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_too_long_id(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 590829034 0.6 6000");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_too_short_id(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 323487 0.8 1000");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_non_integer_id(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 62345.673 0.3 2000");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_duplicate_account_creation(){
        CD newAccount = new CD(55689215,AccountTest.TEST_APR1,AccountTest.TEST_STARTING_BALANCE);
        bank.addAccount(newAccount);
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 55689215 0.4 3000");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_non_numeric_apr(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 18987924 JS.SH 2000 ");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_negative_apr(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 98975843 -0.5 1000 ");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_bigger_than_ten(){
        boolean actual = this.parentCommandValidator.validate("Create checking 98912899 11.2");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_non_numeric_starting_balance(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 62345678 0.7 ABC");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_negative_starting_balance(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 78981114 0.2 -1000");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_starting_balance_smaller_than_thousand_dollars(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 18981115 0.2 10");
        Assertions.assertFalse(actual);
    }

    @Test
    void CD_invalid_create_command_with_starting_balance_bigger_than_ten_thousand_dollars(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 18981115 0.2 20000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_CD_missing_id(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 8.2 5000");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_CD_missing_apr(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 33912829 1500");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_CD_missing_starting_balance(){
        boolean actual = this.parentCommandValidator.validate("Create banking.CD 33912829 3.5");
        Assertions.assertFalse(actual);
    }


}
