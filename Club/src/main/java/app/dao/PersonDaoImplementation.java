package app.dao;

import app.dao.interfaces.PersonDao;
import app.dto.PersonDto;

public class PersonDaoImplementation implements PersonDao {
        
        @Override
        public boolean existsByDocument(PersonDto personDto) throws Exception {
            return personDto.getDocument()==123456789;
        }

        @Override
        public void createPerson(PersonDto personDto) throws Exception {
                System.out.println("Se ha creado la persona");
        }

        @Override
        public void deletePerson(PersonDto personDto)throws Exception {     
                System.out.println("Se ha eliminado la persona");
        }

}