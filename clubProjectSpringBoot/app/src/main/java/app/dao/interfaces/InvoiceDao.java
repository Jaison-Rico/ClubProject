
package app.dao.interfaces;

import app.dto.InvoiceDto;

public interface InvoiceDao {
    public void createInvoice(InvoiceDto invoiceDto) throws Exception;
    
   // public InvoiceDto findByParnert() throws Exception;
}
