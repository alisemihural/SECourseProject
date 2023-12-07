package banking;

public class CreateCommandProcessor {
    private Bank bank;

    public CreateCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processCreate(String accountType, String id, String apr,String startingBalance) {
        int intID = Integer.parseInt(id);
        double doubleApr = Double.parseDouble(apr);
        int intStartingBalance = Integer.parseInt(startingBalance);
        if ("savings".equalsIgnoreCase(accountType)) {
            Savings newSavingsAccount = new Savings(intID, doubleApr);
            bank.addAccount(newSavingsAccount);
        }

        if ("checking".equalsIgnoreCase(accountType)) {
            Checking newCheckingAccount = new Checking(intID, doubleApr);
            bank.addAccount(newCheckingAccount);
        }

        if ("CD".equalsIgnoreCase(accountType)) {
                CD newCDAccount = new CD(intID, doubleApr, intStartingBalance);
                bank.addAccount(newCDAccount);
        }
    }
}
