package app.dao.interfaces;

import app.dto.PersonDto;

public interface PartnerDao {
        public void createGuest(PersonDto personDto) throws Exception;
        public void disableGuest(PersonDto personDto)throws Exception;
        public void enableGuest(PersonDto personDto)throws Exception;
}
