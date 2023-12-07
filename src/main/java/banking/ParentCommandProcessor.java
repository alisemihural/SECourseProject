package banking;

public class ParentCommandProcessor {
    private CreateCommandProcessor createCommandProcessor;
    private DepositCommandProcessor depositCommandProcessor;

    private String accountType;
    private String action;
    private String id;
    private String apr;
    private Integer commandLen;
    private String startingBalance = "0";
    private String depositAmount;

    public ParentCommandProcessor(Bank bank) {
        this.createCommandProcessor = new CreateCommandProcessor(bank);
        this.depositCommandProcessor = new DepositCommandProcessor(bank);
    }

    public void processCommand(String command) {
        String[] commandParts = command.split(" ");
        action = commandParts[0];

        if("Create".equalsIgnoreCase(action)) {
            accountType = commandParts[1];
            id = commandParts[2];
            apr = commandParts[3];
            commandLen = commandParts.length;

            if (commandParts.length == 5) {
                startingBalance = commandParts[4];
            }

            createCommandProcessor.processCreate(accountType,id,apr,startingBalance);
        }

        if("Deposit".equalsIgnoreCase(action)) {
            id = commandParts[1];
            depositAmount = commandParts[2];
            commandLen = commandParts.length;

            depositCommandProcessor.processDeposit(id,depositAmount);
        }
    }

}

