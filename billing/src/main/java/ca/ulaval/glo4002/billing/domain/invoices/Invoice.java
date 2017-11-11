package ca.ulaval.glo4002.billing.domain.invoices;

import java.util.Date;

public class Invoice {
	private Date dueDate;

	public Invoice() {

	}

	public Invoice(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDueDate() {
		return dueDate;
	}
}
