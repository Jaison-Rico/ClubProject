
package app.dao.repository;

import app.dto.UserDto;
import app.model.User;

public interface UserRepository {

    public UserDto findByUserName(String userName);

    public boolean existsByUserName(String userName);

    public void save(User user);

    public void delete(User user);
    
}
