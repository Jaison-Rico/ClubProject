package app.controller;

import app.controller.validator.PersonValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@NoArgsConstructor
@Setter
@Getter
@Controller
public class GuestController implements ControllerInterface{
    
    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
            }
	}
    
    private static final String MENU = "ingrese la opcion que desea realizar "
        + "\n 1. Crear Factura"
        + "\n 2. cerrar sesion";
    
    
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
		this.CreateInvoice();
		return true;
            }
            case "2":{
                System.out.println("se cierra sesion");
                return false;
            }
            default: {
                System.out.println("ingrese un valor valido");
                return true;
            }
        }
    }

    
    private void CreateInvoice() throws Exception {
        System.out.println("crear factura");
    }
         
}
