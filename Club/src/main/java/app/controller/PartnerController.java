package app.controller;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.Service;
import app.service.interfaces.PartnerService;

public class PartnerController implements ControllerInterface  {
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private PartnerValidator partnerValidator;
    private PartnerService service;
    
    private static final String MENU = "ingrese la opcion que desea realizar "
        + "\n 1. Crear invitado"
        + "\n 2. incremento de fondo"
        + "\n 3. solicitud a VIP"
        + "\n 4. habilitar invitado"
        + "\n 5. deshabilitar invitado"
        + "\n 6. cerrar sesion";

    public PartnerController() {
        super();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.partnerValidator = new PartnerValidator();
        this.service = new Service();
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
		this.CreateGuest();
		return true;
            }
            case "2": {
                this.incrementAmount();
		return true;
            }
            case "3":{
                this.vipPromotion();
                return true;
            }
            case "4": {
                System.out.println("activar invitado");
                return true;
            }
            case "5": {
                disableGuest();
                return true;
            }         
            case "6":{
                System.out.println("se cierra sesion");
                return false;
            }
            default: {
                System.out.println("ingrese un valor valido");
                return true;
            }
        }
    }
    
    private void CreateGuest() throws Exception{
        System.out.println("ingrese el nombre del invitado");
            String name = Utils.getReader().nextLine();
            personValidator.validName(name);
            System.out.println("ingrese la cedula del invitado");
            long document = personValidator.validDocument(Utils.getReader().nextLine());
            System.out.println("ingrese el numero de ceular del invitado");
            long cellPhone = personValidator.validCellphone(Utils.getReader().nextLine());
            System.out.println("ingrese el nombre de usuario del invitado");
            String userName = Utils.getReader().nextLine();
            userValidator.validUserName(userName);
            System.out.println("ingrese la contraseña del invitado");
            String password = Utils.getReader().nextLine();
            userValidator.validPassword(password);

            PersonDto personDto = new PersonDto();
            personDto.setName(name);
            personDto.setDocument(document);
            personDto.setCellphone(cellPhone);
            UserDto userDto = new UserDto();
            userDto.setPersonId(personDto);
            userDto.setUserName(userName);
            userDto.setPassword(password);
            userDto.setRole("guest");
            GuestDto guestDto = new GuestDto();
            guestDto.setUserId(userDto);
            guestDto.setStatus(true);
            this.service.createGuest(userDto);
            System.out.println("se ha creado el usuario exitosamente");
    }
    
    private void incrementAmount() throws Exception{
        System.out.println("Ingrese el monto que desea aumentar");
            double amount = Utils.getReader().nextDouble();
     
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setAmount(amount);
    }
    
    private void vipPromotion() throws Exception{
        System.out.println("Ascender socio regular a VIP");    
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setType(true);
    }
    
    private void disableGuest()throws Exception{
        System.out.println("desactivar invitado");
        System.out.println("numero de cedula del invitado");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        this.service.disableGuest(document);
    }
    private void activateGuest()throws Exception{
        System.out.println("desactivar invitado"); 
    }
}
