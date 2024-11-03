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
import app.service.interfaces.PartnerService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.dao.interfaces.InvoiceDao;
import app.dao.interfaces.InvoiceDetailDao;
import app.service.interfaces.GuestService;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Service
public class ClubService implements  AdminService, PartnerService, GuestService {
    
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
    
    


    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        this.createUser(partnerDto.getUserId());
        this.partnerDao.createPartner(partnerDto);
        
        
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        this.createUser(guestDto.getUserId());
        UserDto userDto = this.userDao.findByid(guestDto.getPartnerId().getId());
        guestDto.setPartnerId(partnerDao.findByUserId(userDto));

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
        UserDto userDto = this.userDao.findByid(InvoiceDto.getPartnerId().getId());
        if(userDto.getRole().equals("partner")){
            PartnerDto partnerDto = this.partnerDao.findByUserId(userDto);
            InvoiceDto.setPartnerId(partnerDto);
        }else{
            GuestDto guestDto = this.guestDao.findByUserId(userDto);
            InvoiceDto.setPartnerId(guestDto.getPartnerId()); 
        }
        InvoiceDto.setPersonId(userDto.getPersonId());
        this.invoiceDao.createInvoice(InvoiceDto);
    }

    @Override
    public void promotiontovip() throws Exception {
        List<PartnerDto> listPartnersVip = this.partnerDao.findByType("vip");
        if(listPartnersVip.size() == 5){
            throw new Exception("No se pueden hacer mas promociones a VIP. El maximo de 5 VIPs ya esta ocupado.");
        }
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
        UserDto userDto = this.userDao.findByid(partnerDto.getId());
        this.userDao.convertPartner(userDto);
        GuestDto guestDto = new GuestDto();
        guestDto = this.guestDao.findByUserId(userDto);
        partnerDto.setUserId(userDto);
        this.partnerDao.createPartner(partnerDto);
        this.guestDao.deleteGuest(guestDto);
        
        
    }

    @Override
    public void incrementAmount(PartnerDto partnerDto) throws Exception {
        UserDto userDto = this.userDao.findByid(partnerDto.getId());
        PartnerDto partner = new PartnerDto();
        partner = this.partnerDao.findByUserId(userDto);
        partner.setAmount(partner.getAmount() + partnerDto.getAmount());
        if(partner.getAmount() > 1000000 && partner.isType().equals("regular")){
            throw  new Exception("El tope maximo para socios regulares es de 1 Millon");
        }else if(partner.getAmount() > 5000000 && partner.isType().equals("vip")) {
            throw  new Exception("El tope maximo para socios vips es de 5 Millon");
        }
        
        this.partnerDao.incrementAmount(partner);
    }

    @Override
    public String PartnerRequestVip(PartnerDto partnerDto) throws Exception {
        UserDto userDto = this.userDao.findByid(partnerDto.getId());
        PartnerDto partner = this.partnerDao.findByUserId(userDto);
        partner.setType(partnerDto.isType());
        this.partnerDao.PartnerVipPromotion(partner);
        return "status actual del partner: "+ partner.isType();
    }

    @Override
    public List<InvoiceDetailDto> invoiceHistory() throws Exception {
        List<InvoiceDetailDto> listInvoicesDetailDto = this.invoideDetailDao.listClubInvoices();
        return listInvoicesDetailDto;
        
    }

    @Override
    public List<InvoiceDetailDto> invoiceHistoryPartner(long document) throws Exception {
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
        List<InvoiceDetailDto> listInvoicesDetialPartner = new ArrayList<>();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            if(invoiceDetailDto.getInvoiceId().getPersonId().getDocument() == document){
                listInvoicesDetialPartner.add(invoiceDetailDto);
            }    
        }
        return listInvoicesDetialPartner;
            
    }

    @Override
    public List<InvoiceDetailDto> invoiceHistoryGuest(long document) throws Exception {
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
        List<InvoiceDetailDto> listInvoicesDetialGuest = new ArrayList<>();
        for(InvoiceDetailDto invoiceDetailDto : listInvoicesDetailDto){
            if(invoiceDetailDto.getInvoiceId().getPersonId().getDocument() == document){
                listInvoicesDetialGuest.add(invoiceDetailDto);
            }    
        }
        return listInvoicesDetialGuest;
    }

}
