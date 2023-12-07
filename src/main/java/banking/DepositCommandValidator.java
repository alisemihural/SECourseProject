package banking;

import javax.security.sasl.SaslClient;
import java.util.Objects;

public class DepositCommandValidator {
    private Bank bank;
    public DepositCommandValidator(Bank bank){
        this.bank = bank;
    }

    public boolean validate(String id, String depositAmount, Integer commandLength) {
        if(!(this.validateId(id, commandLength))){
            return false;
        }

        if(!(this.validateDepositAmount(depositAmount,id))){
            return false;
        }

        return true;
    }

    private boolean validateId(String id, Integer commandLength){
        // ID Length is 8
        if(id.length() != 8){
            return false;
        }

        // ID is Integer
        try{
            Integer integerId = Integer.parseInt(id);
        } catch(Exception e) {
            return false;
        }

        return true;
    }

    private boolean validateDepositAmount(String depositAmount, String id) {

        // Deposit amount is double
        double doubleDepositAmount;
        try{
            doubleDepositAmount = Double.parseDouble(depositAmount);
        } catch(Exception e) {
            return false;
        }

        if(doubleDepositAmount<0){
            return false;
        }

        //  Max Deposit Limit for Savings is 2500
        //  Max Deposit Limit for Checking is 1000
        Account account;

        try{
            account = bank.retrieve(Integer.parseInt(id));;
        } catch(Exception e) {
            return false;
        }

        if(account instanceof Savings && doubleDepositAmount > 2500){
            return false;
        }

        if(account instanceof Checking && doubleDepositAmount > 1000){
            return false;
        }

        if(account instanceof CD){
            return false;
        }

        return true;
    }
}
