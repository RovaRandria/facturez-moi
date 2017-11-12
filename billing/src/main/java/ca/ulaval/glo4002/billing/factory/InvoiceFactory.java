package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;

import java.util.Date;

public class InvoiceFactory {
  public Invoice create(Bill bill) {
    final InvoiceId INVOICE_ID = new InvoiceId(bill.getBillId());
    final Date EFFECTIVE_DATE = new Date();
    return new Invoice(INVOICE_ID, EFFECTIVE_DATE, bill.getDueTerm());
  }
}
