package app.service.interfaces;

import app.dto.GuestDto;
import app.dto.PersonDto;
import app.dto.UserDto;

public interface AdminService {
        public void createPartner(UserDto userDto) throws Exception;
        public void createGuest(UserDto userDto) throws Exception;
        public void promotiontovip(PersonDto personDto) throws Exception;
}
