package app.dao;

import app.config.MYSQLConnection;
import app.dto.GuestDto;
import app.helpers.Helper;
import app.model.Guest;
import java.sql.PreparedStatement;
import app.dao.interfaces.GuestDao;

public class GuestDaoImplementation implements GuestDao{
        
        @Override
        public void createGuest(GuestDto guestDto) throws Exception{
                Guest guest = Helper.parse(guestDto);
                String query = "INSERT INTO GUEST(USERID, PARTNERID, STATUS) VALUES (?,?,?)";
                PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
                preparedStatement.setLong(1, guest.getUserId().getId());
                preparedStatement.setLong(2, guest.getPartnerId().getId());
                preparedStatement.setBoolean(3, guest.isStatus());
                
        }
        
        @Override
        public void disableGuest(GuestDto partnerDto) throws Exception{
                throw new UnsupportedOperationException("Not supported yet.");
        }
        
        @Override
        public void enableGuest(GuestDto partnerDto) throws Exception{
                throw new UnsupportedOperationException("Not supported yet.");
        }
}
