package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;

public class InvoiceDtoFactory {

  public static final String BILLS_PATH = "/bills/";

  public InvoiceDto create(InvoiceId invoiceId, Invoice invoice) {
    InvoiceDto invoiceDto = new InvoiceDto(invoiceId.toString(), invoice.getEffectiveDate(),
            invoice.getExpectedPayment(), invoice.getDueTerm(), BILLS_PATH + invoiceId.toString());
    return invoiceDto;
  }
}
