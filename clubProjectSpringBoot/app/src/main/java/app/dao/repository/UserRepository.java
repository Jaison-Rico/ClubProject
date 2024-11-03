
package app.dao.repository;

import app.model.Person;
import app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUserName(String userName);
    public boolean existsByUserName(String userName);
    public User findByPersonId (Person Person);
    public User findById(long id);
    
}
