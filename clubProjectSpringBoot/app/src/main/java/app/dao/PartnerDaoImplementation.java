package app.dao;
import app.dto.PartnerDto;
import app.helpers.Helper;
import app.model.Partner;
import app.dao.interfaces.PartnerDao;
import app.dao.repository.PartnerRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PartnerDaoImplementation implements PartnerDao{
    @Autowired
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
