package banking;

import java.util.Map;
import java.util.Objects;

public class PassTimeCommandProcessor {
    private Bank bank;
    private WithdrawCommandValidator withdrawCommandValidator;

    public PassTimeCommandProcessor(Bank bank){
        this.bank = bank;
        this.withdrawCommandValidator = new WithdrawCommandValidator(bank);
    }

    public void passTime(int months) {

        for (int i = 0; i < months; i++) {
            for (Account account : bank.getBankDB()) {
                // Close the accounts with 0 balance
                if (account.getBalance() == 0) {
                    bank.deleteAccount(account);
                }

                // If Balance is less than 100$ after end of the month deduct 25
                if (account.getBalance() < 100){
                    bank.withdraw(account.getID(),25);
                }

                // Calculate APR
                if(account instanceof CD){
                    double decimalAPR = account.getApr() / 100;
                    double monthlyAPR = decimalAPR / 12;
                    for (int j =0; j<4; j++){
                        double interestAmount = account.getBalance() * monthlyAPR;

                        bank.deposit(account.getID(), interestAmount);
                    }
                }

                if (account instanceof Savings || account instanceof Checking){
                    double decimalAPR = account.getApr() / 100;
                    double monthlyAPR = decimalAPR / 12;
                    double interestAmount = account.getBalance() * monthlyAPR;

                    bank.deposit(account.getID(), interestAmount);
                }

                account.increaseAccountAge();
                account.resetMonthlyWithdrawCount();
            }
        }
    }
}
