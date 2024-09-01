package app.service.interfaces;

import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;

public interface AdminService {
        public void createPartner(PartnerDto partnerDto) throws Exception;
        public void promotiontovip(PersonDto personDto) throws Exception;
}
