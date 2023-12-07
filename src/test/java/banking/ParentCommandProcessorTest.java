package banking;

import org.junit.jupiter.api.BeforeEach;

public class ParentCommandProcessorTest {
    ParentCommandProcessor parentCommandProcessor;
    Bank bank;

    @BeforeEach
    void setUp() {
        this.bank = new Bank();
        this.parentCommandProcessor = new ParentCommandProcessor(bank);
    }

}
