
package app.helpers;

import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Guest;
import app.model.Partner;
import app.model.Person;
import app.model.User;
import java.sql.Date;

public abstract interface Helper {
    public static PersonDto parse(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setDocument(person.getDocument());
        personDto.setName(person.getName());
        personDto.setCellphone(person.getCellphone());
        return personDto;
    }
	
    public static Person parse(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setDocument(personDto.getDocument());
        person.setName(personDto.getName());
        person.setCellphone(personDto.getCellphone());
        return person;
    }
	
    public static UserDto parse(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setPersonId(parse(user.getPersonId()));
        userDto.setRole(user.getRole());
        return userDto;
    }
	
    public static User parse(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setPersonId(parse(userDto.getPersonId()));
        user.setRole(userDto.getRole());
        user.setUserName(userDto.getUserName());
        return user;
    }
    
    public static PartnerDto parse(Partner partner) {
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId(partner.getId());
        partnerDto.setUserId(parse(partner.getUserId()));
        partnerDto.setAmount(partner.getAmount());
        partnerDto.setType(partner.isType());
        partnerDto.setCreationDate((Date) partner.getCreationDate());
        return partnerDto;
    }
    
    public static Partner parse(PartnerDto partnerDto) {
        Partner partner = new Partner();
        partner.setId(partnerDto.getId());
        partner.setUserId(parse(partnerDto.getUserId()));
        partner.setAmount(partnerDto.getAmount());
        partner.setType(partnerDto.isType());
        partner.setCreationDate(partnerDto.getCreationDate());
        return partner;
    }
    
    public static GuestDto parse(Guest guest) {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setPartnerId(parse(guest.getPartnerId()));
        guestDto.setStatus(guest.isStatus());
        return guestDto;
    }
    
    public static Guest parse(GuestDto guestDto) {
        Guest guest = new Guest();
        guest.setId(guestDto.getId());
        guest.setPartnerId(parse(guestDto.getPartnerId()));
        guest.setStatus(guestDto.isStatus());
        return guest;
    }
}
