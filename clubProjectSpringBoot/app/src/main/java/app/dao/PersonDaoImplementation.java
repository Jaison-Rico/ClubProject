package app.dao;

import app.dao.repository.PersonRepository;
import app.dao.interfaces.PersonDao;
import app.dto.PersonDto;
import app.helpers.Helper;
import app.model.Person;


public class PersonDaoImplementation implements PersonDao {
    public PersonRepository personRepository;
    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {
        return personRepository.existsByDocument(personDto.getDocument());
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
	personRepository.save(person);
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

}