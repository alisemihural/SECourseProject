package banking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class WithdrawCommandValidator {
    private Bank bank;
    private Integer integerId;
    private ArrayList savingsAccountThatWithdrawn;

    public WithdrawCommandValidator(Bank bank) {
        this.bank = bank;
    }

    public boolean validate(String id, String withdrawAmount, Integer commandLength) {
        if(!(this.validateId(id,withdrawAmount))){
            return false;
        }

        if(!(this.validateWithdrawAmount(withdrawAmount))){
            return false;
        }
        return true;
    }

    public boolean validateId(String id, String withdrawAmount){
        Integer integerId = 0;

        // ID Length is 8
        if(id.length() != 8){
            return false;
        }

        // ID is Integer
        try{
            integerId = Integer.parseInt(id);
        } catch(Exception e) {
            return false;
        }

        if(bank.retrieve(Integer.parseInt(id)) == null){
            return false;
        }

        Account account = bank.retrieve(integerId) ;

        // Savings account can only withdraw once a month
        if(account instanceof Savings && account.getMonthlyWithdrawCount() != 0) {
            return false;
        }

        // CD accounts can withdraw after 12 month they created
        if(account instanceof CD) {
            if(account.getAccountAge() < 13){
                return false;
            }

            if (Double.parseDouble(withdrawAmount) < account.getBalance()){
                return false;
            }
        }

        return true;
    }

    public boolean validateWithdrawAmount(String withdrawAmount){

        // Withdraw amount is double
        double doubleWithdrawAmount;
        try{
            doubleWithdrawAmount = Double.parseDouble(withdrawAmount);
        } catch(Exception e) {
            return false;
        }

        if(doubleWithdrawAmount < 0){
            return false;
        }

        return true;

    }
}
