
package app.dao.interfaces;

import app.dto.PersonDto;

public  interface PersonDao {
    public boolean ExistsByDocument(PersonDto personDto) throws Exception;
    public void createPErson(PersonDto personDto) throws Exception;
    public void deletePerson(PersonDto personDto) throws Exception;
}
