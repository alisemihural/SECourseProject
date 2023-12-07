package banking;

public class PassTimeCommandValidator {
    public boolean validate(String months) {
        if(this.validateMonths(months)){
            return true;
        }

        return false;
    }

    private boolean validateMonths(String months) {
        Integer intMonths;

        try{
            intMonths = Integer.parseInt(months);
        } catch(Exception e) {
            return false;
        }

        if(intMonths<1 || intMonths>60){
            return false;
        }

        return true;
    }
}
