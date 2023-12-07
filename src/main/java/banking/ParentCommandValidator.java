package banking;

import java.util.Objects;

public class ParentCommandValidator {
    private CreateCommandValidator createCommandValidator;
    private DepositCommandValidator depositCommandValidator;
    private WithdrawCommandValidator withdrawCommandValidator;
    private PassTimeCommandValidator passTimeCommandValidator;

    private String accountType;
    private String action;
    private String id;
    private String apr;
    private Integer commandLen;
    private String startingBalance = "0";
    private String depositAmount;
    private String withdrawAmount;
    private String months;

    public ParentCommandValidator(Bank bank){
        this.createCommandValidator = new CreateCommandValidator(bank);
        this.withdrawCommandValidator = new WithdrawCommandValidator(bank);
        this.depositCommandValidator = new DepositCommandValidator(bank);
        this.passTimeCommandValidator = new PassTimeCommandValidator();
    }

    public boolean validate(String command){

        String[] commandParts = command.split(" ");
        action = commandParts[0];

        if("Create".equalsIgnoreCase(action)) {
            try{
                accountType = commandParts[1];
                id = commandParts[2];
                apr = commandParts[3];
                commandLen = commandParts.length;
            } catch (Exception e){
                return false;
            }

            if (commandParts.length == 5) {
                if (Objects.equals(accountType,"banking.CD")) {
                    try {
                        startingBalance = commandParts[4];
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            return createCommandValidator.validate(id, apr, startingBalance, accountType, commandLen);
        }

        if("Deposit".equalsIgnoreCase(action)) {
            try {
                id = commandParts[1];
                depositAmount = commandParts[2];
                commandLen = commandParts.length;
            } catch (Exception e){
                return false;
            }

            if (!(commandLen == 3)){
                return false;
            }

            return depositCommandValidator.validate(id, depositAmount, commandLen);
        }

        if("Withdraw".equalsIgnoreCase(action)) {
            try {
                id = commandParts[1];
                withdrawAmount = commandParts[2];
                commandLen = commandParts.length;
            } catch (Exception e) {
                return false;
            }

            if (!(commandLen == 3)){
                return false;
            }

            return depositCommandValidator.validate(id, withdrawAmount, commandLen);
        }

        if("Pass".equalsIgnoreCase(action)) {
            months = commandParts[1];
            commandLen = commandParts.length;

            if (!(commandLen == 2)){
                return false;
            }

            return passTimeCommandValidator.validate(months);
        }

        return false;
    }

    public String getAction(){
        return action;
    }

}
