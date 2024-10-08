package app.dao;

import app.config.MYSQLConnection;
import app.dto.GuestDto;
import app.helpers.Helper;
import app.model.Guest;
import java.sql.PreparedStatement;
import app.dao.interfaces.GuestDao;
import app.dao.repository.GuestRepository;
import app.dto.PartnerDto;
import app.model.Partner;
import app.model.User;
import java.sql.ResultSet;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
    @Override
    public void enableGuest(GuestDto guestDto) throws Exception{
        throw new UnsupportedOperationException("Not supported yet.");
    }


    

    
   }
