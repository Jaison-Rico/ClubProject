
package app.dao.repository;

import app.model.Partner;

public interface PartnerRepository {

    public void Delete(Partner partner);

    public void save(Partner partner);
    
}
