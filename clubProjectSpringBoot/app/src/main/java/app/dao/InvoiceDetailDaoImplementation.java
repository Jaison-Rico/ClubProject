
package app.dao;

import app.dao.interfaces.InvoiceDetailDao;
import app.dao.repository.InvoiceDetailRepository;
import app.dto.InvoiceDetailDto;
import app.helpers.Helper;
import app.model.InvoiceDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class InvoiceDetailDaoImplementation implements InvoiceDetailDao{
    @Autowired
    public InvoiceDetailRepository invoiceDetailRepository;
    
     public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception{
        InvoiceDetail invoiceDetail = Helper.parse(invoiceDetailDto);
        invoiceDetailRepository.save(invoiceDetail);
    }
    
    
}
