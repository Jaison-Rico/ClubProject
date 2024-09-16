package app.controller.validator;

public class PartnerValidator extends CommonsValidator{
    public double validAmount(String amount) throws Exception{
        return super.isValidDouble("La cantidad del socio", amount);
    }
    
}
