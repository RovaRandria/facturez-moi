package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;

public class InvoiceService {

  private InvoiceRepository invoiceRepository;

  public InvoiceService() {
    invoiceRepository = new HibernateInvoiceRepository();
  }

  public InvoiceService(InvoiceRepository invoiceRepository) {
    this.invoiceRepository = invoiceRepository;
  }

  public InvoiceDto createInvoice(int billId) {
    return null;
  }

  public boolean invoiceExists(BillId billId) {
    boolean billExists = false;

    Invoice invoice = invoiceRepository.findInvoice(billId);

    if (invoice != null) {
      billExists = true;
    }

    return billExists;
  }
}
