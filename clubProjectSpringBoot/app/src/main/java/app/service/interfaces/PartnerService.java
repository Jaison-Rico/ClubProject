
package app.service.interfaces;


import app.dto.GuestDto;
import app.dto.UserDto;

public interface PartnerService {
    public void createGuest (GuestDto guestDto) throws Exception;
    public void disableGuest(long document) throws Exception;
}
