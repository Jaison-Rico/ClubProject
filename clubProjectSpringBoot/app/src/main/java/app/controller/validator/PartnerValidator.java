package app.controller.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;



@Getter
@Setter
@NoArgsConstructor
@Component
public class PartnerValidator extends CommonsValidator{
    public double validAmount(String amount) throws Exception{
        return super.isValidDouble("La cantidad del socio", amount);
    }
    
}
