package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParentCommandValidatorTest {
    ParentCommandValidator parentCommandValidator;
    Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.parentCommandValidator = new ParentCommandValidator(bank);
    }

    // VALID Delegation Tests
    @Test
    void is_this_command_create(){
        boolean command = this.parentCommandValidator.validate("Create savings 92748294 0.1");
        String actual = this.parentCommandValidator.getAction();
        Assertions.assertEquals(actual,"Create");
    }

    @Test
    void is_this_command_deposit(){
        boolean command = this.parentCommandValidator.validate("Deposit 12345678 100");
        String actual = this.parentCommandValidator.getAction();
        Assertions.assertEquals(actual,"Deposit");
    }

    @Test
    void is_this_command_withdraw(){
        boolean command = this.parentCommandValidator.validate("Deposit 12345678 100");
        String actual = this.parentCommandValidator.getAction();
        Assertions.assertEquals(actual,"Deposit");
    }

    @Test
    void is_this_command_passTime(){
        boolean command = this.parentCommandValidator.validate("Pass 12");
        String actual = this.parentCommandValidator.getAction();
        Assertions.assertEquals(actual,"Pass");
    }

    // INVALID Missing Command

    @Test
    void missing_command(){
        boolean actual = this.parentCommandValidator.validate("12345678 100");
        Assertions.assertFalse(actual);
    }


    // INVALID Typo's in Command

    @Test
    void invalid_typo_in_deposit() {
        boolean actual = this.parentCommandValidator.validate("Depoosit 98912891 300");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_typo_in_create() {
        boolean actual = this.parentCommandValidator.validate("Creeaate 98912891 300");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_typo_in_passTime() {
        boolean actual = this.parentCommandValidator.validate("Paass 12");
        Assertions.assertFalse(actual);
    }

    // INVALID Too Many Arguments

    @Test
    void invalid_deposit_too_many_arguments() {
        boolean actual = this.parentCommandValidator.validate("Deposit 253392836 100 800");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_passTime_too_many_arguments(){
        boolean actual = this.parentCommandValidator.validate("Pass 10 10");
        Assertions.assertFalse(actual);
    }

    // INVALID Not Enough Arguments

    @Test
    void invalid_deposit_not_enough_arguments() {
        boolean actual = this.parentCommandValidator.validate("Deposit 253392836");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_withdraw_not_enough_arguments() {
        boolean actual = this.parentCommandValidator.validate("Withdraw 253392836");
        Assertions.assertFalse(actual);
    }
}
