package app.controller;

import app.controller.validator.InvoiceValidator;

import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.service.interfaces.GuestService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@NoArgsConstructor
@Setter
@Getter
@Controller
public class GuestController implements ControllerInterface{
    
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private GuestService service;
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
		this.createInvoice();
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
        invoiceDto.setStatus(false);
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
         
}
