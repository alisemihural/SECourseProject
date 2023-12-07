package banking;

import java.util.Objects;

public class CreateCommandValidator {

    private Bank bank;

    public CreateCommandValidator(Bank bank){
        this.bank = bank;
    }


    public boolean validate(String id, String apr, String startingBalance, String accountType, Integer commandLength) {

        if(!(this.validateId(id,accountType,commandLength))){
            return false;
        }

        if(!(this.validateApr(apr))) {
            return false;
        }
        
        if(Objects.equals(accountType,"banking.CD")){
            if(!(this.validateStartingBalance(startingBalance,accountType,commandLength))) {
                return false;
            }
        }

        return true;
    }

    private boolean validateId(String id, String accountType, Integer commandLength){
        if(id.length() != 8){
            return false;
        }

        if(Objects.equals(accountType, "savings") && commandLength != 4){
            return false;
        }

        if(id.indexOf('.') != -1){
            return false;
        }

        try{
            Integer integerId = Integer.parseInt(id);
        } catch(Exception e) {
            return false;
        }

        if(bank.retrieve(Integer.parseInt(id)) != null){
            return false;
        }

        return true;
    }

    private boolean validateApr(String apr){

        try{
            Float floatApr = Float.parseFloat(apr);
        } catch(Exception e) {
            return false;
        }

        if( Float.parseFloat(apr)<0 || Float.parseFloat(apr)>10 ){
            return false;
        }

        return true;

    }

    private boolean validateStartingBalance(String startingBalance, String accountType, Integer commandLength) {

        try{
            Integer integerStartingBalance = Integer.parseInt(startingBalance);
        } catch(Exception e) {
            return false;
        }

        if(Objects.equals(accountType, "banking.CD") && commandLength != 5){
            return false;
        }

        if(startingBalance.indexOf('.') != -1){
            return false;
        }

        if(Integer.parseInt(startingBalance)<1000 || Integer.parseInt(startingBalance) > 10000){
            return false;
        }

        return true;
    }
}
