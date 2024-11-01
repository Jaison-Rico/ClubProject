
package app.dao;

import app.dao.interfaces.InvoiceDetailDao;
import app.dao.repository.InvoiceDetailRepository;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.helpers.Helper;
import app.model.Invoice;
import app.model.InvoiceDetail;
import java.util.ArrayList;
import java.util.List;
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
    
      @Override
    public List<InvoiceDetailDto> listClubInvoices() throws Exception {
        List<InvoiceDetail> listInvoicesDetial = new ArrayList<>();
        List<InvoiceDetailDto> listInvoicesDetialDto = new ArrayList<>();
        listInvoicesDetial = invoiceDetailRepository.findAll();
        for(InvoiceDetail invoiceDetail : listInvoicesDetial){
            listInvoicesDetialDto.add(Helper.parse(invoiceDetail));
        }
        return listInvoicesDetialDto;
    }
}
