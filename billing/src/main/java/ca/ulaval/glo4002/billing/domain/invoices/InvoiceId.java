package ca.ulaval.glo4002.billing.domain.invoices;

import java.io.Serializable;

import javax.persistence.Embeddable;

import ca.ulaval.glo4002.billing.domain.bills.BillId;

@Embeddable
public class InvoiceId implements Serializable {
	private static final long serialVersionUID = 1L;
	private BillId invoiceId;

	public InvoiceId() {
		// For hibernate. Do not use.
	}

	@Override
	public String toString() {
		return invoiceId.toString();
	}

	public InvoiceId(BillId id) {
		this.invoiceId = id;
	}

	public BillId getId() {
		return invoiceId;
	}
}
