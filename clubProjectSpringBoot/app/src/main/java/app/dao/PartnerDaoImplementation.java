package app.dao;
import app.dto.PartnerDto;
import app.helpers.Helper;
import app.model.Partner;
import app.dao.interfaces.PartnerDao;
import app.dao.repository.PartnerRepository;


public class PartnerDaoImplementation implements PartnerDao{
    public PartnerRepository partnerRepository;
    @Override    
    public void createPartner(PartnerDto partnerDto) throws Exception{
            Partner partner = Helper.parse(partnerDto);
            partnerRepository.save(partner);
        }

        @Override
        public void deletePartner(PartnerDto partnerDto) throws Exception {
            Partner partner = Helper.parse(partnerDto);
            partnerRepository.delete(partner);
        }
}
