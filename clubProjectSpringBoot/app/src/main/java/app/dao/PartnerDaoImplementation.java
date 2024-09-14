package app.dao;
import app.config.MYSQLConnection;

import app.dto.PartnerDto;

import app.helpers.Helper;
import app.model.Partner;
import java.sql.PreparedStatement;

import app.dao.interfaces.PartnerDao;
import java.sql.Date;

public class PartnerDaoImplementation implements PartnerDao{
        
    @Override    
    public void createPartner(PartnerDto partnerDto) throws Exception{
        Partner partner = Helper.parse(partnerDto);
        String query = "INSERT INTO PARTNER(USERID,AMOUNT,TYPE,CREATIONDATE) VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partner.getUserId().getId());
        preparedStatement.setDouble(2, partner.getAmount());
        preparedStatement.setBoolean(3, partner.isType());
        preparedStatement.setDate(4, (Date) partner.getCreationDate());
        preparedStatement.execute();
        preparedStatement.close();
        }

        @Override
        public void deletePartner(PartnerDto partnerDto) throws Exception {
            throw new UnsupportedOperationException("Not supported yet.");
        }
}
