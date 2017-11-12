package ca.ulaval.glo4002.billing.repository;

import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.payments.PaymentRepository;

public class HibernatePaymentRepository implements PaymentRepository {

	@Override
	public void insert(Invoice invoice) {
	}
}
