package ca.ulaval.glo4002.billing.domain.invoices;

import java.util.List;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;

public interface InvoiceRepository {

  Invoice find(InvoiceId invoiceId);

  InvoiceId insert(Invoice invoice);

  List<Invoice> findInvoices(ClientId clientId);
}
