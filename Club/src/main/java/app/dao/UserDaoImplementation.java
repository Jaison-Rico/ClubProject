package app.dao;

import app.dao.interfaces.UserDao;
import app.dto.UserDto;

public class UserDaoImplementation implements UserDao{   
	
	@Override
	public UserDto findByUserName(UserDto userDto) throws Exception {
		UserDto validateDto = new UserDto();
		if(userDto.getUserName().equals("admin")) {
			validateDto.setUserName(userDto.getUserName());
			validateDto.setRole(userDto.getUserName());
			validateDto.setPassword("admin");
			return validateDto;
		}
		if (userDto.getUserName().equals(userDto.getPassword())) {
			validateDto.setUserName(userDto.getUserName());
			validateDto.setRole(userDto.getUserName());
			validateDto.setPassword(userDto.getUserName());
			return validateDto;
		}
		return null;
	}

	@Override
	public boolean existsByUserName(UserDto userDto) throws Exception {
		return userDto.getUserName().equals("rogelio");
	}

	@Override
	public void createUser(UserDto userDto) throws Exception {
		System.out.println("se ha registrado el usuario");
	}

    
}
