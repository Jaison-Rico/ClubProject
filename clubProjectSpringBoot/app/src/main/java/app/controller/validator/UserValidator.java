
package app.controller.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@Component
public class UserValidator extends CommonsValidator{
    public void validUserName(String username ) throws  Exception {
        super.isValidString("El nombre de usuario", username);
        
    }
    
    public void validPassword(String password) throws Exception{
        super.isValidString("La contraseña del usario", password);
    }
    
    public void validPAssword(String role) throws Exception{
        super.isValidString("el rol de  usuario", role);
    }
    
}
