package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeCommandValidatorTest {
    PassTimeCommandValidator passTimeCommandValidator;

    @BeforeEach
    void setUp() {
        this.passTimeCommandValidator = new PassTimeCommandValidator();
    }

    private boolean validateCommand(String command) {
        String[] parts = command.split(" ");
        String months = parts.length > 1 ? parts[1] : "";
        return this.passTimeCommandValidator.validate(months);
    }

    // VALID Pass Time Commands
    @Test
    void valid_pass_time_command(){
        boolean actual = validateCommand("Pass 12");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_months_is_1(){
        boolean actual = validateCommand("Pass 1");
        Assertions.assertTrue(actual);
    }

    @Test
    void valid_months_is_60(){
        boolean actual = validateCommand("Pass 60");
        Assertions.assertTrue(actual);
    }

    // VALID Case Insensitivity

    @Test
    void valid_case_insensitivity(){
        boolean actual = validateCommand("pass 12");
        Assertions.assertTrue(actual);
    }

    // INVALID Months

    @Test
    void invalid_months_is_0(){
        boolean actual = validateCommand("Pass 0");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_months_is_61(){
        boolean actual = validateCommand("Pass 0");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_months_is_smaller_than_0(){
        boolean actual = validateCommand("Pass -5");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_months_is_bigger_than_60(){
        boolean actual = validateCommand("Pass 70");
        Assertions.assertFalse(actual);
    }

    @Test
    void invalid_months_is_non_numeric(){
        boolean actual = validateCommand("Pass ABC");
        Assertions.assertFalse(actual);
    }


}
