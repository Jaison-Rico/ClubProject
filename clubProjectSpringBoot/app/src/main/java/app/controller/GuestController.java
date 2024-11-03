package app.controller;

import app.controller.validator.InvoiceValidator;

import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
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
public class GuestController{
    
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private GuestService service;
    
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
    
    private void convertPartner() throws Exception {
        PartnerDto parnetDto = new PartnerDto();
        UserDto userDto = new UserDto();
        System.out.println("ingrese el fondo inial");
        double amount = invoiceValidator.validAmount(Utils.getReader().nextLine());
        parnetDto.setUserId(userDto);
        parnetDto.setAmount(amount);
        parnetDto.setType("regular");
        parnetDto.setCreationDate(Utils.getDate());
        System.out.println("acabas de convertirse en socio");
        this.service.convertPartner(parnetDto);
        
        
    }
         
}
