package banking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateCommandProcessorTest {
    ParentCommandProcessor parentCommandProcessor;
    Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.parentCommandProcessor = new ParentCommandProcessor(bank);
    }

    ////////////////////////SAVINGS///////////////////////

    @Test
    void create_command_creates_the_saving_account(){
        String command = "Create savings 12345678 1.0";
        parentCommandProcessor.processCommand(command);
        Savings savingsAccount = (Savings) bank.retrieve(12345678);

        Assertions.assertNotNull(savingsAccount);
        Assertions.assertEquals(savingsAccount.getApr(),1.0);
        Assertions.assertEquals(savingsAccount.getID(),12345678);
    }

    ////////////////////////CHECKING//////////////////////

    @Test
    void create_command_creates_the_checking_account(){
        String command = "Create checking 87654321 3.0";
        parentCommandProcessor.processCommand(command);
        Checking checkingAccount = (Checking) bank.retrieve(87654321);

        Assertions.assertNotNull(checkingAccount);
        Assertions.assertEquals(checkingAccount.getApr(),3.0);
        Assertions.assertEquals(checkingAccount.getID(),87654321);
    }

    ////////////////////////banking.CD////////////////////////////

    @Test
    void create_command_creates_the_CD_account(){
        String command = "Create CD 18273645 5.2 3000";
        parentCommandProcessor.processCommand(command);
        CD CDAccount = (CD) bank.retrieve(18273645);

        Assertions.assertNotNull(CDAccount);
        Assertions.assertEquals(CDAccount.getApr(),5.2);
        Assertions.assertEquals(CDAccount.getID(),18273645);
        Assertions.assertEquals(CDAccount.getBalance(),3000);
    }
}
