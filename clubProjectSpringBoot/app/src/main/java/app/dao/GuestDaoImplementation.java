package app.dao;


import app.dto.GuestDto;
import app.helpers.Helper;
import app.model.Guest;
import app.dao.interfaces.GuestDao;
import app.dao.repository.GuestRepository;
import app.dto.UserDto;
import app.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Getter
@Setter
@NoArgsConstructor
@Service
public class GuestDaoImplementation implements GuestDao{
    @Autowired
    public GuestRepository guestRepository;   
    @Override
    public void createGuest(GuestDto guestDto) throws Exception{
        Guest guest = Helper.parse(guestDto);
        guestRepository.save(guest);        
    }
        
    @Override
    public void disableGuest(GuestDto guestDto) throws Exception{
        Guest guest = Helper.parse(guestDto);
        guest.setStatus("inactiva");
        guestRepository.save(guest);
    }
        
    @Override
    public void enableGuest(GuestDto guestDto) throws Exception{
        Guest guest = Helper.parse(guestDto);
        guest.setStatus("activa");
        guestRepository.save(guest);
    }

    @Override
    public GuestDto findByUserId(UserDto userDto) throws Exception {
       User user = Helper.parse(userDto);
       Guest guest = guestRepository.findByUserId(user);
       if (guest == null)
           return null;
       return Helper.parse(guest);
    }

    @Override
    public void deleteGuest(GuestDto guestDto) throws Exception {
        Guest guest = Helper.parse(guestDto);
        guestRepository.delete(guest);
    }

    


    

    
   }
