package app.dao.interfaces;

import app.dto.GuestDto;

public interface GuestDao {
        public void createGuest(GuestDto partnerDto) throws Exception;
        public void disableGuest(GuestDto partnerDto) throws Exception;
        public void enableGuest(GuestDto partnerDto) throws Exception;
}
