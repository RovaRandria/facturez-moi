package ca.ulaval.glo4002.billing.domain.invoices;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;

public interface InvoiceRepository {
	Invoice find(BillId billId);

	void insert(Invoice invoice);

	Invoice findLast(ClientId clientId);
}
