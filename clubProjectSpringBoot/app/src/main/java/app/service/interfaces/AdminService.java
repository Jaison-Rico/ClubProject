package app.service.interfaces;


import app.dto.PartnerDto;


public interface AdminService {
        public void createPartner(PartnerDto partnerDto) throws Exception;
        public void promotiontovip( ) throws Exception;
        public void invoiceHistory () throws Exception;
        public void invoiceHistoryPartner(long document) throws Exception;
        public void invoiceHistoryGuest(long document) throws Exception;
}
