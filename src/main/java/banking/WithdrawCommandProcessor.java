package banking;

public class WithdrawCommandProcessor {
    private Bank bank;
    private PassTimeCommandProcessor passTimeCommandProcessor;

    public WithdrawCommandProcessor(Bank bank) {
        this.bank = bank;
    }

    public void processWithdraw(String id, String withdrawAmount) {
        int intID = Integer.parseInt(id);
        double doubleDepositAmount = Double.parseDouble(withdrawAmount);

        Account account = bank.retrieve(intID);

        // If the account is CD all the balance should be withdrawn
        if(account instanceof CD){
            bank.withdraw(intID, account.getBalance());
        } else {
            bank.withdraw(intID, doubleDepositAmount);
        }
    }
}
