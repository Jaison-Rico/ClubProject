
package app.dao.interfaces;

import app.dto.InvoiceDto;
import java.util.List;

public interface InvoiceDao {
    public void createInvoice(InvoiceDto invoiceDto) throws Exception;
    public  List<InvoiceDto> listClubInvoices() throws Exception;
   
}
