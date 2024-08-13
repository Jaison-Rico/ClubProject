
package app.controller;

import app.controller.validator.UserValidator;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.LoginService;
import java.util.HashMap;
import java.util.Map;

public class LoginController implements ControllerInterface{
    private UserValidator userValidator;
    private LoginService service;
    
    private static final String MENU = "ingrese la opcion que desea:"
        + "\n1.inicar sesion"
        + "\n2. detener la ejecucion";
    
    private Map<String, ControllerInterface> roles;    
    public LoginController(){
        this.userValidator = new UserValidator();
        this.service = new Service();
        
        ControllerInterface adminController = new AdminController();
        ControllerInterface guestController = new GuestController();
        ControllerInterface partnerController = new PartnerController();
        this.roles = new HashMap<String, ControllerInterface>();
        roles.put("admin",adminController);
        roles.put("guest",guestController);
        roles.put("partner",partnerController);      
    }
    
    
   public void session() throws Exception {
	boolean session = true;
	while (session) {
            session = menu();
            }
	}

    private boolean menu() {
        try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            return this.options(option);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
		}
	}

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
		this.login();
		return true;
                }
            case "2": {
                System.out.println("Se detiene el programa");
		return false;
		
		}
            default: {
		System.out.println("ingrese un valor valido");
		return true;
		}
            }
	}
    
    private void login() throws Exception{
        System.out.println("Ingrese el usuario");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);
        System.out.println("Ingrese la contraseña");
        String  password = Utils.getReader().nextLine();
        userValidator.validPassword(password);
        UserDto userDto = new UserDto();
        userDto.setPassword(password);
        userDto.setUserName(userName);
        
        this.service.login(userDto);
        if(roles.get(userDto.getRole()) == null){
            throw new Exception("Rol invalido");
        }
        roles.get(userDto.getRole()).session();
    }
    
}
