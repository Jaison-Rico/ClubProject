package app.dao;
import app.config.MYSQLConnection;
import app.dao.interfaces.PartnerDao;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.helpers.Helper;
import app.model.Partner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import app.dao.interfaces.PartnerDao;

public class PartnerDaoImplementation implements PartnerDao{
        @Override    
        public void createPartner(PartnerDto partnerDto) throws Exception{
            
        }

        @Override
        public void deletePartner(PartnerDto partnerDto) throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
}
