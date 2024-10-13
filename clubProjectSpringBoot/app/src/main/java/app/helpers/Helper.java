package app.helpers;

import app.dto.GuestDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Guest;
import app.model.Invoice;
import app.model.InvoiceDetail;
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
        if (userDto.getPersonId() != null) {
            user.setPersonId(parse(userDto.getPersonId()));
        }
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
        partnerDto.setCreationDate(partner.getCreationDate());
        return partnerDto;
    }

    public static Partner parse(PartnerDto partnerDto) {
        Partner partner = new Partner();
        partner.setId(partnerDto.getId());
        if (partnerDto.getUserId() != null) {
            partner.setUserId(parse(partnerDto.getUserId()));
        }
        partner.setAmount(partnerDto.getAmount());
        partner.setType(partnerDto.isType());
        partner.setCreationDate( (Date) partnerDto.getCreationDate());
        return partner;
    }

    public static GuestDto parse(Guest guest) {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setPartnerId(parse(guest.getPartnerId()));
        guestDto.setStatus(guest.isStatus());
        guestDto.setUserId(parse(guest.getUserId()));
        return guestDto;
    }

    public static Guest parse(GuestDto guestDto) {
        Guest guest = new Guest();
        guest.setId(guestDto.getId());
        if (guestDto.getPartnerId() != null) {
            guest.setPartnerId(parse(guestDto.getPartnerId()));
        }
        guest.setStatus(guestDto.isStatus());
        if (guestDto.getUserId() != null) {
            guest.setUserId(parse(guestDto.getUserId()));
        }
        return guest;
    }
    
    public static InvoiceDto parse(Invoice invoice){
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId(invoice.getId());
        invoiceDto.setPersonId(parse(invoice.getPersonId()));
        invoiceDto.setPartnerId(parse(invoice.getPartnerId()));
        invoiceDto.setCreationDate(invoice.getCreationDate());
        invoiceDto.setAmount(invoice.getAmount());
        invoiceDto.setStatus(invoice.isStatus());
        return invoiceDto;
    }
    
    public static Invoice parse(InvoiceDto invoiceDto){
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDto.getId());
        if(invoiceDto.getPersonId()!= null){
            invoice.setPersonId(parse(invoiceDto.getPersonId()));
        }
        if(invoiceDto.getPartnerId()!= null){
            invoice.setPartnerId(parse(invoiceDto.getPartnerId()));
        }
        invoice.setCreationDate((Date) invoiceDto.getCreationDate());
        invoice.setAmount(invoiceDto.getAmount());
        invoice.setStatus(invoiceDto.isStatus());
        return invoice;
    }
    
    
    public static InvoiceDetailDto parse(InvoiceDetail invoiceDetail ){
        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setId(invoiceDetail.getId());
        if(invoiceDetailDto.getInvoiceId() != null){    
            invoiceDetailDto.setInvoiceId(parse(invoiceDetail.getInvoiceId()));
        }
        invoiceDetailDto.setItem(invoiceDetail.getItem());
        invoiceDetailDto.setDescription(invoiceDetail.getDescription());
        invoiceDetailDto.setAmount(invoiceDetail.getAmount());
        return  invoiceDetailDto;
    }
    
    public static InvoiceDetail parse(InvoiceDetailDto invoiceDetailDto ){
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setId(invoiceDetailDto.getId());
        invoiceDetail.setInvoiceId(parse(invoiceDetailDto.getInvoiceId()));
        invoiceDetail.setItem(invoiceDetailDto.getItem());
        invoiceDetail.setDescription(invoiceDetailDto.getDescription());
        invoiceDetail.setAmount(invoiceDetailDto.getAmount());
        return  invoiceDetail;
    }
}
