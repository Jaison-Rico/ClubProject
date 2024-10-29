
package app.dao.repository;

import app.model.Partner;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartnerRepository extends JpaRepository<Partner,Long> {

    public Partner findByUserId(User user);
    
    
}
