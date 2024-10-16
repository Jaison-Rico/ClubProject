package app.controller;

import app.controller.validator.InvoiceValidator;
import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.ClubService;
import app.service.interfaces.PartnerService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@NoArgsConstructor
@Setter
@Getter
@Controller
public class PartnerController implements ControllerInterface  {
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PartnerValidator partnerValidator;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private PartnerService service;
    
    private static final String MENU = "ingrese la opcion que desea realizar "
        + "\n 1. Crear invitado"
        + "\n 2. habilitar invitado"
        + "\n 3. deshabilitar invitado"
        + "\n 4. incremento de fondo"
        + "\n 5. hacer consumo"
        + "\n 6. solicitud a VIP"
        + "\n 7. cerrar sesion";

   

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
                this.enableGuest();
		return true;
            }
            case "3":{
               this.disableGuest();
                return true;
            }
            case "4": {
                this.incrementAmount();
                return true;
            }
            case "5": {
                createInvoice();
                return true;
            }
            case "6":{
                this.vipPromotion();
                return true;
            }
            case "7":{
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
        PartnerDto partnerDto = new PartnerDto();
        GuestDto guestDto = new GuestDto();
        guestDto.setUserId(userDto);
        guestDto.setPartnerId(partnerDto);
        guestDto.setStatus("inactiva");
        this.service.createGuest(guestDto);
        System.out.println("se ha creado el usuario exitosamente");
    }
    private void createInvoice() throws Exception {
        System.out.println("ingrese el item de la factura");  
        int item = invoiceValidator.validItem(Utils.getReader().nextLine());
        System.out.println("ingrese la descripcion de la factura");  
        String description = Utils.getReader().nextLine();
        invoiceValidator.validDescription(description);
        System.out.println("ingrese el valor de la factura");  
        double amount = invoiceValidator.validAmount(Utils.getReader().nextLine());
        
        PersonDto personDto = new PersonDto();
        PartnerDto partnerDto = new PartnerDto();
        //
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setPersonId(personDto);
        invoiceDto.setPartnerId(partnerDto);
        invoiceDto.setStatus("Sin pagar");
        invoiceDto.setAmount(amount);
        invoiceDto.setCreationDate(Utils.getDate()); 
        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setInvoiceId(invoiceDto);
        invoiceDetailDto.setItem(item);
        invoiceDetailDto.setDescription(description);
        invoiceDetailDto.setAmount(amount);
        this.service.createInvoiceDetail(invoiceDetailDto);
        System.out.println("se ha creado la factura exitosamente");
        
    }
    private void incrementAmount() throws Exception{
        System.out.println("Ingrese el monto que desea aumentar");
        double amount = partnerValidator.validAmount(Utils.getReader().nextLine());   
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setAmount(amount);
        this.service.incrementAmount(partnerDto);
    }
    private void vipPromotion() throws Exception{
        System.out.println("Ascender socio regular a VIP");    
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setType("vip");
    }
    private void disableGuest()throws Exception{
        System.out.println("desactivar invitado");
        System.out.println("numero de cedula del invitado");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        this.service.disableGuest(document);
        System.out.println("usuario desactivado");
    }
    private void enableGuest()throws Exception{
        System.out.println("activar invitado");
        System.out.println("numero de cedula del invitado");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        this.service.enableGuest(document);
        System.out.println("usuario activado");
    }
    
}
