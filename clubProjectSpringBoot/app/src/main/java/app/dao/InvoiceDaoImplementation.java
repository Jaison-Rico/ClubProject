
package app.dao;

import app.dao.repository.InvoiceRepository;
import app.dto.InvoiceDto;
import app.helpers.Helper;
import app.model.Invoice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.dao.interfaces.InvoiceDao;
import app.dto.InvoiceDetailDto;
import app.model.InvoiceDetail;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Service
public class InvoiceDaoImplementation implements InvoiceDao{
    @Autowired
    public InvoiceRepository invoiceRepository;
    
    
    
    public void createInvoice(InvoiceDto invoiceDto) throws Exception{
        Invoice invoice = Helper.parse(invoiceDto);
        invoiceRepository.save(invoice);
        invoiceDto.setId(invoice.getId());
    }

    @Override
    public List<InvoiceDto> listClubInvoices() throws Exception {
        List<Invoice> listInvoices = new ArrayList<>();
        List<InvoiceDto> listInvoiceDto = new ArrayList<>();
        listInvoices = invoiceRepository.findAll();
        for(Invoice invoice : listInvoices){
            listInvoiceDto.add(Helper.parse(invoice));
        }
        return listInvoiceDto;
    }

  
    
}
