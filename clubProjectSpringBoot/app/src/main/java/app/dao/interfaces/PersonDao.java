package app.dao.interfaces;

import app.dto.PersonDto;
import app.dto.UserDto;

public interface PersonDao {
    public boolean existsByDocument(PersonDto personDto) throws Exception;
    public void createPerson(PersonDto personDto) throws Exception;
    public void deletePerson(PersonDto personDto) throws Exception;
    public PersonDto findByDocument(PersonDto personDto) throws Exception;
    public PersonDto findByUserId(UserDto userDto) throws Exception;
}
