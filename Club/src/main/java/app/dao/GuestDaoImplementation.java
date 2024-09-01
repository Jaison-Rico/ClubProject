package app.dao;

import app.config.MYSQLConnection;
import app.dto.GuestDto;
import app.helpers.Helper;
import app.model.Guest;
import java.sql.PreparedStatement;
import app.dao.interfaces.GuestDao;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.model.Partner;
import app.model.User;
import java.sql.ResultSet;

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
    public void disableGuest(GuestDto guestDto) throws Exception{
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
    @Override
    public void enableGuest(GuestDto guestDto) throws Exception{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PartnerDto findPartnerWhitUserId(PartnerDto partnerDto) throws Exception {
       String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setAmount(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getBoolean("TYPE"));
            partner.setCreationDate(resulSet.getDate("CREATIONDATE"));    
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            partner.setUserId(user); 
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }
}
