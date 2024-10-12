package app.dao;

import app.dao.repository.PersonRepository;
import app.dao.interfaces.PersonDao;
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
public class PersonDaoImplementation implements PersonDao {
    @Autowired
    public PersonRepository personRepository;
    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {
        return personRepository.existsByDocument(personDto.getDocument());
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
	personRepository.save(person);
        personDto.setId(person.getId());
        
    }

    @Override
    public void deletePerson(PersonDto personDto)throws Exception {     
        Person person = Helper.parse(personDto);
        personRepository.delete(person);
    }

    @Override
    public PersonDto findByDocument(PersonDto personDto) throws Exception {
       Person person = personRepository.findByDocument(personDto.getDocument());
       return Helper.parse(person);
    }

    @Override
    public PersonDto findByUserId(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        Person person = personRepository.findByUserId(user);
        if (person == null)
            return null;
        return Helper.parse(person);
    }

}