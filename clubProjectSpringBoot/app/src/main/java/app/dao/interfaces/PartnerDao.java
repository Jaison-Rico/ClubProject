package app.dao.interfaces;

import app.dto.PartnerDto;
import app.dto.UserDto;

public interface PartnerDao {
    public void createPartner(PartnerDto partnerDto) throws Exception;
    public void deletePartner(PartnerDto partnerDto) throws Exception;
    public PartnerDto findByUserId(UserDto userDto) throws Exception;
    public void incrementAmount(PartnerDto partnerDto) throws Exception;
    public void vipPromotion(PartnerDto partnerDto) throws Exception;
}
