package app.controller;

import app.controller.request.CreationInvoiceRequest;
import app.controller.request.CreationUserRequest;
import app.controller.request.PostIncrementAmount;
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
import app.service.interfaces.PartnerService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@NoArgsConstructor
@Setter
@Getter
@Controller
public class PartnerController{
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
    
    
    @PostMapping("/create-guest")
    private ResponseEntity CreateGuest( @RequestBody CreationUserRequest request ) throws Exception{
        try {
            String name = request.getName();
            personValidator.validName(name);
            long document = personValidator.validDocument(request.getDocument());
            long cellPhone = personValidator.validCellphone(request.getCellPhone());
            String userName = request.getUserName();
            userValidator.validUserName(userName);
            String password = request.getPassword();
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
            partnerDto.setId(request.getUserSesion());
            GuestDto guestDto = new GuestDto();
            guestDto.setUserId(userDto);
            guestDto.setPartnerId(partnerDto);
            guestDto.setStatus("inactiva");
            this.service.createGuest(guestDto);
            return new ResponseEntity<>("se ha creado el usuario exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    } 
    
    
    
    
    
    @PostMapping("/increment-amount")
    private ResponseEntity incrementAmount(@RequestBody PostIncrementAmount request) throws Exception{
        try {
            double amount = partnerValidator.validAmount(request.getAmount());   
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId(request.getUserSesion());
            partnerDto.setAmount(amount);
            this.service.incrementAmount(partnerDto);
            return ResponseEntity.ok("Se incrementaron los fondos correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    
    private void PartnerRequestVip() throws Exception{
        System.out.println("Ascender socio regular a VIP");    
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setType("pendiente");
        this.service.PartnerRequestVip(partnerDto);
        System.out.println("solicitud enviada");
    }
    
    
    
    
    
    
    
    @GetMapping("disable-guest/{document}")
    private ResponseEntity disableGuest(@PathVariable long document)throws Exception{
        try {
            this.service.disableGuest(document);
            return ResponseEntity.ok("se ha desactivado correctamente");
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
        
    }
    @GetMapping("enable-guest/{document}")
    private ResponseEntity enableGuest(@PathVariable long document)throws Exception{
        try {
            this.service.enableGuest(document);
            return ResponseEntity.ok("se ha activado correctamente");
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); 
        }
    }

    
}
