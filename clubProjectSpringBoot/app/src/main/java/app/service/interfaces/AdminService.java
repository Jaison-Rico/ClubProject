package app.service.interfaces;


import app.dto.InvoiceDetailDto;
import app.dto.PartnerDto;
import java.util.List;


public interface AdminService {
        public void createPartner(PartnerDto partnerDto) throws Exception;
        public void promotiontovip( ) throws Exception;
        public List<InvoiceDetailDto> invoiceHistory () throws Exception;
        public void invoiceHistoryPartner(long document) throws Exception;
        public void invoiceHistoryGuest(long document) throws Exception;
}
