package app.service;


import app.dao.GuestDaoImplementation;
import app.dao.PartnerDaoImplementation;
import app.dao.PersonDaoImplementation;
import app.dao.UserDaoImplementation;
import app.dao.interfaces.GuestDao;
import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.PersonDao;
import app.dao.interfaces.UserDao;
import app.dto.GuestDto;
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


@Getter
@Setter
@NoArgsConstructor
@Service
public class ClubService implements LoginService, AdminService, PartnerService {
    
    @Autowired
    private UserDao userDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PartnerDao partnerDao;
    @Autowired
    private GuestDao guestDao;
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
        partnerDto.setUserId(userDao.findByUserName(partnerDto.getUserId()));
        if(partnerDto.getAmount() < 50000){
            this.userDao.deleteUser(partnerDto.getUserId());
            throw  new Exception("El monto inical tiene que ser minimo 50000");  
        }
        this.partnerDao.createPartner(partnerDto);
        
        
    }

    @Override
    public void createGuest(GuestDto GuestDto) throws Exception {
        this.createUser(GuestDto.getUserId());
        this.guestDao.createGuest(GuestDto);
        
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
    public void promotiontovip(PersonDto personDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void disableGuest(long document) throws Exception {
        GuestDto guestDto = new GuestDto();
        guestDto.setStatus(false);
    }



}
