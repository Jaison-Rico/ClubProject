package app.controller;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.AdminService;

public  class AdminController implements ControllerInterface{
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private PartnerValidator partnerValidator;
    private AdminService service;
    private static final String MENU = "ingrese la opcion que desea realizar "
        + "\n 1. para crear Socio "
        + "\n 2. Historial de facturas"
        + "\n 3. promocion a VIP"
        + "\n 4. cerrar sesion";

    public AdminController() {
        super();
        this.service = new Service();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.partnerValidator = new PartnerValidator();
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
		this.CreatePartner();
		return true;
                }
            case "2": {
                System.out.println("comming soon");
		return true;
		}
            case "3":{
                System.out.println("comming soon");
                return true;
                }
            case "4": {
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
        partnerDto.setType(true);
        partnerDto.setAmount(amount);
        partnerDto.setCreationDate(Utils.getDate());   
        this.service.createPartner(partnerDto);
	System.out.println("se ha creado el usuario exitosamente");
    }  
}
