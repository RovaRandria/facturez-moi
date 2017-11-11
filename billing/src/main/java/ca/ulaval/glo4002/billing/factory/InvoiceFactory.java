package ca.ulaval.glo4002.billing.factory;

import java.util.Date;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;

public class InvoiceFactory {
	public Invoice create(Bill bill) {
		final InvoiceId invoiceId = new InvoiceId(bill.getBillId());
		final Date effectiveDate = new Date();
		return new Invoice(invoiceId, effectiveDate, bill.getDueTerm());
	}
}
