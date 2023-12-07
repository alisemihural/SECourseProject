package banking;

public class DepositCommandProcessor {
    private Bank bank;

    public DepositCommandProcessor(Bank bank) {
        this.bank = bank;
    }
    public void processDeposit(String id, String depositAmount) {
        int intID = Integer.parseInt(id);
        double doubleDepositAmount = Double.parseDouble(depositAmount);

        bank.deposit(intID,doubleDepositAmount);
    }
}
