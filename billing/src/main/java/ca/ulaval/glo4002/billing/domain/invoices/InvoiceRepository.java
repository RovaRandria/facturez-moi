package ca.ulaval.glo4002.billing.domain.invoices;

import ca.ulaval.glo4002.billing.domain.bills.BillId;

public interface InvoiceRepository {
	Invoice find(BillId billId);

	void insert(Invoice invoice);
}
