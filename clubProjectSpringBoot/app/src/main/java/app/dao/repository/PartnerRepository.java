
package app.dao.repository;

import app.model.Partner;
import app.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartnerRepository extends JpaRepository<Partner,Long> {

    public Partner findByUserId(User user);
    public List<Partner> findByType(String type);
    
}
