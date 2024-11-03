
package app.dao.interfaces;

import app.dto.PersonDto;
import app.dto.UserDto;


public interface UserDao {
    public UserDto findByUserName(UserDto userDto) throws Exception;
    public boolean existsByUserName(UserDto userDto) throws Exception;
    public void createUser(UserDto userDto) throws Exception;
    public void deleteUser(UserDto userDto) throws Exception;
    public UserDto findByPersonId (PersonDto personDto) throws Exception;
    public void convertPartner(UserDto userDto) throws Exception;
    public UserDto findByid(long id) throws Exception;
}
