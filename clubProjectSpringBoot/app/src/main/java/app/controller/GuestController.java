package app.controller;

import app.controller.request.CreationInvoiceRequest;
import app.controller.request.PostIncrementAmount;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@NoArgsConstructor
@Setter
@Getter
@Controller
public class GuestController{
    
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private GuestService service;

    
    
    @PostMapping("/create-invoice")
    private ResponseEntity createInvoice(@RequestBody CreationInvoiceRequest request) throws Exception {  
        try {
            int item = invoiceValidator.validItem(request.getItem());
            String description = request.getDescription();
            invoiceValidator.validDescription(description); 
            double amount = invoiceValidator.validAmount(request.getAmount());

            PersonDto personDto = new PersonDto();
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId(request.getUserSesion());
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
            return ResponseEntity.ok("se ha creado la factura exitosamente");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }
    @PutMapping("/convert-partner")
    private ResponseEntity convertPartner(@RequestBody PostIncrementAmount request) throws Exception {
        try {
            PartnerDto parnetDto = new PartnerDto();
            UserDto userDto = new UserDto();
            double amount = invoiceValidator.validAmount(request.getAmount());
            parnetDto.setId(request.getUserSesion());
            parnetDto.setUserId(userDto);
            parnetDto.setAmount(amount);
            parnetDto.setType("regular");
            parnetDto.setCreationDate(Utils.getDate());
            this.service.convertPartner(parnetDto);
            return ResponseEntity.ok("acabas de convertirse en socio");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
        
    }
         
}
