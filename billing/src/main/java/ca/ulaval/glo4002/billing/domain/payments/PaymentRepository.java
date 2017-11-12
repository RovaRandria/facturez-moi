package ca.ulaval.glo4002.billing.domain.payments;

import ca.ulaval.glo4002.billing.domain.invoices.Invoice;

public interface PaymentRepository {
	void insert(Invoice invoice);
}
