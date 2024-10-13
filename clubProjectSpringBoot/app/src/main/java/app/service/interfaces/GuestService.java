
package app.service.interfaces;

import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;

public interface GuestService {
    public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;
    public void createInvoice(InvoiceDto invoiceDto) throws Exception;
    public void convertPartner(PartnerDto partnerDto) throws Exception;
}
