package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CommandStorageTest {
    CommandStorage commandStorage;
    Bank bank;

    @BeforeEach
    void setUp() {
        this.commandStorage = new CommandStorage();
    }

    @Test
    void command_storage_returns_one_invalid_command(){

        String command = "Creeate 123456789 1.0";

        commandStorage.addInvalidCommand(command);

        ArrayList<String> actual = commandStorage.getInvalidCommands();

        System.out.println(commandStorage.getInvalidCommands());
        Assertions.assertEquals(command,actual.get(0));
    }
}
