package app.dao.repository;

import app.model.Guest;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository{


    public void findPartnerWhitUserId(User userId);

    public void save(Guest guest);
}
