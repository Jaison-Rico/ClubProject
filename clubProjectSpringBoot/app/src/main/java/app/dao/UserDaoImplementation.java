package app.dao;


import app.dao.interfaces.UserDao;
import app.dao.repository.UserRepository;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.helpers.Helper;
import app.model.Person;
import app.model.User; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class UserDaoImplementation implements UserDao{   
    @Autowired
    public UserRepository UserRepository;
    @Override
    public UserDto findByUserName(UserDto userDto) throws Exception {
         // user admin :D    
        UserDto validateDto = new UserDto();
        if(userDto.getUserName().equals("admin")) {
            validateDto.setUserName(userDto.getUserName());
            validateDto.setRole(userDto.getUserName());
            validateDto.setPassword("admin");
            return validateDto;
        }
        return Helper.parse( UserRepository.findByUserName(userDto.getUserName()));
        
    }

    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        return UserRepository.existsByUserName(userDto.getUserName());
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        UserRepository.save(user);
        userDto.setId(user.getId());
    }

    @Override
    public void deleteUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
	UserRepository.delete(user);
    }

    @Override
    public UserDto findByPersonId(PersonDto personDto) throws Exception {
       Person person = Helper.parse(personDto);
       User user = UserRepository.findByPersonId(person);
        if (user == null)
            return null;
        return Helper.parse(user);
    }
}