package app.service;



import app.dao.interfaces.GuestDao;
import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.PersonDao;
import app.dao.interfaces.UserDao;
import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfaces.AdminService;
import app.service.interfaces.LoginService;
import app.service.interfaces.PartnerService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.dao.interfaces.InvoiceDao;
import app.dao.interfaces.InvoiceDetailDao;
import app.service.interfaces.GuestService;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Service
public class ClubService implements LoginService, AdminService, PartnerService, GuestService {
    
    @Autowired
    private UserDao userDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PartnerDao partnerDao;
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private InvoiceDetailDao invoideDetailDao;
    public static UserDto user;
    
    
    
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
	if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
	}
	userDto.setRole(validateDto.getRole());
	user = validateDto;
    }

    @Override
    public void logout() {
        user = null;
        System.out.println("Se ha cerrado session");
    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        this.createUser(partnerDto.getUserId());
        this.partnerDao.createPartner(partnerDto);
        
        
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        this.createUser(guestDto.getUserId());
        guestDto.setPartnerId(partnerDao.findByUserId(user));  
        this.guestDao.createGuest(guestDto);
        
    }
    
    private void createUser(UserDto userDto) throws Exception{
        this.createPerson(userDto.getPersonId());
	if(this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("ya existe un usuario con ese user name");
        }
        this.userDao.createUser(userDto);
            
    }
   
    private void createPerson(PersonDto personDto)throws Exception{
	if(this.personDao.existsByDocument(personDto)) {
            throw new Exception("ya existe una persona con ese documento");
	}
        this.personDao.createPerson(personDto);
    }
    @Override
    public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
      this.createInvoice(invoiceDetailDto.getInvoiceId());
      this.invoideDetailDao.createInvoiceDetail(invoiceDetailDto);
    }
    
    @Override
    public void createInvoice(InvoiceDto InvoiceDto) throws Exception {
        if(user.getRole().equals("partner")){
            PartnerDto partnerDto = this.partnerDao.findByUserId(user);
            InvoiceDto.setPartnerId(partnerDto);
        }else{
            GuestDto guestDto = this.guestDao.findByUserId(user);
            InvoiceDto.setPartnerId(guestDto.getPartnerId()); 
        }
        InvoiceDto.setPersonId(user.getPersonId());
        this.invoiceDao.createInvoice(InvoiceDto);
    }

    @Override
    public void promotiontovip() throws Exception {
        List<PartnerDto> listPartners = this.partnerDao.findByType("pendiente");
        for(PartnerDto partnerDto : listPartners){
            partnerDto.setType("vip");
            this.partnerDao.PartnerVipPromotion(partnerDto);
        }
    }

    @Override
    public void disableGuest(long document) throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setDocument(document);
        personDto = this.personDao.findByDocument(personDto);
        UserDto userDto = new UserDto();
        userDto = this.userDao.findByPersonId(personDto);
        GuestDto guestDto = new GuestDto();
        guestDto = this.guestDao.findByUserId(userDto);
        this.guestDao.disableGuest(guestDto);
    }

    @Override
    public void enableGuest(long document) throws Exception {
        
        PersonDto personDto = new PersonDto();
        personDto.setDocument(document);
        if(this.personDao.existsByDocument(personDto) == false) {
            throw new Exception("no existe una persona con ese documento");
	}
        personDto = this.personDao.findByDocument(personDto);
        UserDto userDto = new UserDto();
        userDto = this.userDao.findByPersonId(personDto);
        GuestDto guestDto = new GuestDto();
        guestDto = this.guestDao.findByUserId(userDto);
        this.guestDao.enableGuest(guestDto);
    }

    @Override
    public void convertPartner(PartnerDto partnerDto) throws Exception {
        this.userDao.convertPartner(user);
        GuestDto guestDto = new GuestDto();
        guestDto = this.guestDao.findByUserId(user);
        partnerDto.setUserId(user);
        this.partnerDao.createPartner(partnerDto);
        this.guestDao.deleteGuest(guestDto);
        
        
    }

    @Override
    public void incrementAmount(PartnerDto partnerDto) throws Exception {
        PartnerDto partner = new PartnerDto();
        partner = this.partnerDao.findByUserId(user);
        partner.setAmount(partner.getAmount() + partnerDto.getAmount());
        if(partner.getAmount() > 1000000 && partner.isType().equals("regular")){
            throw  new Exception("El tope maximo para socios regulares es de 1 Millon");
        }else if(partner.getAmount() > 5000000 && partner.isType().equals("vip")) {
            throw  new Exception("El tope maximo para socios vips es de 5 Millon");
        }
        
        this.partnerDao.incrementAmount(partner);
        System.out.println("valor actual del fondo del socio: " + partner.getAmount());
    }

    @Override
    public void PartnerRequestVip(PartnerDto partnerDto) throws Exception {
        PartnerDto partner = this.partnerDao.findByUserId(user);
        partner.setType(partnerDto.isType());
        this.partnerDao.PartnerVipPromotion(partner);
        System.out.println("status actual del partner: "+ partner.isType());
    }

    @Override
    public void invoiceHistory() throws Exception {
        List<InvoiceDetailDto> listInvoicesDetailDto = this.invoideDetailDao.listClubInvoices();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            System.out.println("######################");
            System.out.println("ENCABEZADO DE LA FACTURA"
                + "\nID: " + invoiceDetailDto.getInvoiceId().getId()
                + "\nDOCUMENTO: " + invoiceDetailDto.getInvoiceId().getPersonId().getDocument()
                + "\nSOCIO: " + invoiceDetailDto.getInvoiceId().getPartnerId().getUserId().getUserName()
                + "\nFEHCA: " + invoiceDetailDto.getInvoiceId().getCreationDate()
                + "\nVALOR TOTAL: " + invoiceDetailDto.getInvoiceId().getAmount()
                + "\nESTADO: " + invoiceDetailDto.getInvoiceId().isStatus()
                + "\nDETALLES DE LA FACTURA"
                + "\nID: " + invoiceDetailDto.getId()
                + "\nENCABEZADO ID: " + invoiceDetailDto.getInvoiceId().getId()
                + "\nNUMERO DEL ITEM: " + invoiceDetailDto.getItem()
                + "\nDESCRIPCION: " + invoiceDetailDto.getDescription()
                + "\nVALOR DEL ITEM: " + invoiceDetailDto.getAmount());
        }
        
    }

    @Override
    public void invoiceHistoryPartner(long document) throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.setDocument(document);
        if(this.personDao.existsByDocument(personDto) == false) {
            throw new Exception("no existe una persona con ese documento");
	}
        personDto = this.personDao.findByDocument(personDto);
        UserDto userDto = new UserDto();
        userDto = this.userDao.findByPersonId(personDto);
        if(!"partner".equals(userDto.getRole())){
            throw new Exception("Esta persona no es un socio");
        }
        List<InvoiceDetailDto> listInvoicesDetailDto = this.invoideDetailDao.listClubInvoices();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            if(invoiceDetailDto.getInvoiceId().getPersonId().getDocument() == document){
                System.out.println("######################");
                System.out.println("ENCABEZADO DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nDOCUMENTO: " + invoiceDetailDto.getInvoiceId().getPersonId().getDocument()
                    + "\nSOCIO: " + invoiceDetailDto.getInvoiceId().getPartnerId().getUserId().getUserName()
                    + "\nFEHCA: " + invoiceDetailDto.getInvoiceId().getCreationDate()
                    + "\nVALOR TOTAL: " + invoiceDetailDto.getInvoiceId().getAmount()
                    + "\nESTADO: " + invoiceDetailDto.getInvoiceId().isStatus()
                    + "\nDETALLES DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getId()
                    + "\nENCABEZADO ID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nNUMERO DEL ITEM: " + invoiceDetailDto.getItem()
                    + "\nDESCRIPCION: " + invoiceDetailDto.getDescription()
                    + "\nVALOR DEL ITEM: " + invoiceDetailDto.getAmount());
            }    
        }
            
    }

    @Override
    public void invoiceHistoryGuest(long document) throws Exception {
      PersonDto personDto = new PersonDto();
        personDto.setDocument(document);
        if(this.personDao.existsByDocument(personDto) == false) {
            throw new Exception("no existe una persona con ese documento");
	}
        personDto = this.personDao.findByDocument(personDto);
        UserDto userDto = new UserDto();
        userDto = this.userDao.findByPersonId(personDto);
        if(!"guest".equals(userDto.getRole())){
            throw new Exception("Esta persona no es un invitado");
        }
        List<InvoiceDetailDto> listInvoicesDetailDto = this.invoideDetailDao.listClubInvoices();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            if(invoiceDetailDto.getInvoiceId().getPersonId().getDocument() == document){
                System.out.println("######################");
                System.out.println("ENCABEZADO DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nDOCUMENTO: " + invoiceDetailDto.getInvoiceId().getPersonId().getDocument()
                    + "\nSOCIO: " + invoiceDetailDto.getInvoiceId().getPartnerId().getUserId().getUserName()
                    + "\nFEHCA: " + invoiceDetailDto.getInvoiceId().getCreationDate()
                    + "\nVALOR TOTAL: " + invoiceDetailDto.getInvoiceId().getAmount()
                    + "\nESTADO: " + invoiceDetailDto.getInvoiceId().isStatus()
                    + "\nDETALLES DE LA FACTURA"
                    + "\nID: " + invoiceDetailDto.getId()
                    + "\nENCABEZADO ID: " + invoiceDetailDto.getInvoiceId().getId()
                    + "\nNUMERO DEL ITEM: " + invoiceDetailDto.getItem()
                    + "\nDESCRIPCION: " + invoiceDetailDto.getDescription()
                    + "\nVALOR DEL ITEM: " + invoiceDetailDto.getAmount());
            }    
        }  
    }

}
