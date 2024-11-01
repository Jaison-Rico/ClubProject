package app.controller;

import app.controller.request.CreationUserRequest;
import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.AdminService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Getter
@Setter
@NoArgsConstructor
@RestController
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

 
    @PostMapping("/partner")
    private ResponseEntity CreatePartner(@RequestBody CreationUserRequest request) throws Exception{
        
        try {
            String name = request.getName();
            personValidator.validName(name);
            long document = personValidator.validDocument(request.getDocument());
            long cellPhone = personValidator.validCellphone(request.getCellPhone());
            String userName = request.getUserName();
            userValidator.validUserName(userName);
            String password = request.getPassword();
            userValidator.validPassword(password);
            double amount = partnerValidator.validAmount(request.getAmount());

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
            return new ResponseEntity<>("se ha creado el usuario exitosamente", HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
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
