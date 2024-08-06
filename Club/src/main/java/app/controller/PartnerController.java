package app.controller;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.GuestDto;
import app.dto.PersonDto;
import app.dto.UserDto;

public class PartnerController implements ControllerInterface  {
    private PersonValidator personValidator;
    private UserValidator userValidator;
    
    private static final String MENU = "ingrese la opcion que desea realizar "
        + "\n 1. Crear invitado"
        + "\n 2. incremento de fondo"
        + "\n 3. solicitud a VIP"
        + "\n 4. baja de socio"
        + "\n 5. cerrar sesion";

    public PartnerController() {
        super();
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
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
                System.out.println("comming soon");
		return true;
            }
            case "3":{
                System.out.println("comming soon");
                return true;
            }
            case "4": {
                System.out.println("comming soon");
                return true;
            }
            case "5":{
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
            System.out.println("ingrese el fondo inical del invitado");
            double amount = Utils.getReader().nextDouble();

            PersonDto personDto = new PersonDto();
            personDto.setName(name);
            personDto.setDocument(document);
            personDto.setCellphone(cellPhone);
            UserDto userDto = new UserDto();
            userDto.setPersonid(personDto);
            userDto.setUserName(userName);
            userDto.setPassword(password);
            userDto.setRole("guest");
            GuestDto guestDto = new GuestDto();
            guestDto.setUserId(userDto);
            guestDto.setStatus(true);
            System.out.println("se ha creado el usuario exitosamente");
    }
}
