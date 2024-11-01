package app.controller;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.ClubService;
import app.service.interfaces.AdminService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Getter
@Setter
@NoArgsConstructor
@Controller
public  class AdminController implements ControllerInterface{
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PartnerValidator partnerValidator;
    @Autowired
    private AdminService service;
    private static final String MENU = "ingrese la opcion que desea realizar "
        + "\n 1. para crear Socio "
        + "\n 2. Historial de facturas club"
        + "\n 3. promocion a VIP"
        + "\n 4. Historial facturas socios" 
        + "\n 5. Historial facturas invitado"
        + "\n 6. cerrar sesion";


    
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
		this.CreatePartner();
		return true;
                }
            case "2": {
                this.invoiceHistory();
		return true;
		}
            case "3":{
                this.promotiontovip();
                return true;
                }
            case "4":{
                this.invoiceHistoryPartner();
                return true;
                }
            case "5":{
                this.invoiceHistoryGuest();
                return true;
                }
            case "6": {
                System.out.println("se cierra sesion");
		return false;
		}
            default: {
		System.out.println("ingrese una opcion valido");
		return true;
		}
            }
	}
    
    private void CreatePartner() throws Exception{
        System.out.println("ingrese el nombre del socio");
        String name = Utils.getReader().nextLine();
	personValidator.validName(name);
	System.out.println("ingrese la cedula del socio");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
	System.out.println("ingrese el numero de ceular del socio");
	long cellPhone = personValidator.validCellphone(Utils.getReader().nextLine());
	System.out.println("ingrese el nombre de usuario del socio");
	String userName = Utils.getReader().nextLine();
	userValidator.validUserName(userName);
	System.out.println("ingrese la contraseña del socio");
	String password = Utils.getReader().nextLine();
	userValidator.validPassword(password);
        System.out.println("ingrese el fondo inical del socio");  
        double amount = partnerValidator.validAmount(Utils.getReader().nextLine());
        
	PersonDto personDto = new PersonDto();
	personDto.setName(name);
	personDto.setDocument(document);
	personDto.setCellphone(cellPhone);
	UserDto userDto = new UserDto();
	userDto.setPersonId(personDto);
	userDto.setUserName(userName);
	userDto.setPassword(password);
	userDto.setRole("partner");          
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
        partnerDto.setType("regular");
        partnerDto.setAmount(amount);
        partnerDto.setCreationDate(Utils.getDate());   
        this.service.createPartner(partnerDto);
	System.out.println("se ha creado el usuario exitosamente");
    }
    private void invoiceHistory() throws Exception{
        System.out.println("Historial de facturas");
        this.service.invoiceHistory();
    }
    private void invoiceHistoryPartner() throws Exception{
        System.out.println("historial de facturas del socio: ");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        this.service.invoiceHistoryPartner(document);
    }
    private void invoiceHistoryGuest() throws Exception{
        System.out.println("Documento del invitado: ");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        this.service.invoiceHistoryGuest(document);
    }
    private void promotiontovip() throws Exception{
        this.service.promotiontovip();
        System.out.println("Usuarios promovidos");
    }
}
