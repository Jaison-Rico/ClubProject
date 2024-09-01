package app.dao.interfaces;

import app.dto.PartnerDto;

public interface PartnerDao {
    public void createPartner(PartnerDto partnerDto) throws Exception;
    public void deletePartner(PartnerDto partnerDto) throws Exception;
        
}
