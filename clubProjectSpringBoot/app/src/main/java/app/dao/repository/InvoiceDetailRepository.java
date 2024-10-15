
package app.dao.repository;


import app.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long>{
    
}
