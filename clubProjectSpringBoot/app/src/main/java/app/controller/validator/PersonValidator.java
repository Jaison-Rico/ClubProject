
package app.controller.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;



@Getter
@Setter
@NoArgsConstructor
@Component
public class PersonValidator extends CommonsValidator{
    public void validName(String name) throws Exception {
        super.isValidString("El nombre de la persona", name);
    }
    
    public long validDocument(String document) throws Exception{
        return super.isValidLong("La cedula de la persona", document);
    }
    
    public long validCellphone (String Cellphone) throws Exception{
        return super.isValidLong("el numero de telefono de la persona", Cellphone);
    }
    
}
