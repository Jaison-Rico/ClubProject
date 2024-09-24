package app.dao.repository;

import app.model.Guest;
import app.model.Partner;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long>{


    public Guest findByPartnerId(Partner partner);

}
