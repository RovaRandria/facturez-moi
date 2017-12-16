package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;

public class InvoiceFactory {

  public Invoice create(Bill bill) {
    return new Invoice(bill);
  }
}
