
package app.dao.interfaces;

import app.dto.PersonDto;
import app.dto.UserDto;


public interface UserDao {
    public UserDto findByUserName(UserDto userDto) throws Exception;
    public boolean existsByUserName(UserDto userDto) throws Exception;
    public void createUser(UserDto userDto) throws Exception;
    
}
