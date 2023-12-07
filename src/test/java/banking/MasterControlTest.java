package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MasterControlTest {

    MasterControl masterControl;
    private List<String> input;

    @BeforeEach
    void setUp(){
        input = new ArrayList<>();
        Bank bank = new Bank();
        masterControl = new MasterControl(new ParentCommandValidator(bank), new ParentCommandProcessor(bank), new CommandStorage());
    }

    private void assertSingleCommand(String command, List<String> actual){
        Assertions.assertEquals(1,actual.size());
        Assertions.assertEquals(command, actual.get(0));
    }

    @Test
    void typo_in_create_command_is_invalid(){
        input.add("creat checking 12345678 1.0");

        List<String> actual = masterControl.start(input);
        assertSingleCommand("creat checking 12345678 1.0",actual);
    }

    @Test
    void typo_in_deposit_command_is_invalid(){
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);

        Assertions.assertEquals(1,actual.size());
        assertSingleCommand("depositt 12345678 100",actual);
    }

    @Test
    void two_typo_in_commands_both_invalid(){
        input.add("creat checking 12345678 1.0");
        input.add("depositt 12345678 100");

        List<String> actual = masterControl.start(input);

        Assertions.assertEquals(2,actual.size());
        Assertions.assertEquals("creat checking 12345678 1.0",actual.get(0));
        Assertions.assertEquals("depositt 12345678 100",actual.get(1));
    }

    @Test
    void invalid_to_create_accounts_with_same_ID(){
        input.add("create checking 12345678 1.0");
        input.add("create checking 12345678 1.0");

        List<String> actual = masterControl.start(input);

        assertSingleCommand("create checking 12345678 1.0",actual);

    }
}
