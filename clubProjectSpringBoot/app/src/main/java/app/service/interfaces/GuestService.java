
package app.service.interfaces;

import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;

public interface GuestService {
    public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;
    public void createInvoice(InvoiceDto invoiceDto) throws Exception;
}
