
package app.controller.validator;


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
