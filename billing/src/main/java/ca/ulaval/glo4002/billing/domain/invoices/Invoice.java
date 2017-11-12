package ca.ulaval.glo4002.billing.domain.invoices;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

@Entity(name = "Invoice")
public class Invoice {
	@Id
	@Embedded
	private InvoiceId id;
	@Column(name = "effectiveDate")
	private Date effectiveDate;
	@Column(name = "expectedPayment")
	private Date expectedPayment;
	@Enumerated(EnumType.STRING)
	private DueTerm dueTerm;

	public Invoice() {
	}

	public Invoice(InvoiceId id, Date effectiveDate, DueTerm dueTerm) {
		this.id = id;
		this.effectiveDate = effectiveDate;
		this.dueTerm = dueTerm;
		this.expectedPayment = calculateExpectedPayment();
	}

	public InvoiceId getId() {
		return this.id;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public DueTerm getDueTerm() {
		return this.dueTerm;
	}

	public Date getExpectedPayment() {
		return this.expectedPayment;
	}

	private Date calculateExpectedPayment() {
		Calendar dueDate = Calendar.getInstance();
		int daysToAdd = DueTerm.convertToInt(dueTerm);
		dueDate.setTime(effectiveDate);
		dueDate.add(Calendar.DATE, daysToAdd);
		return dueDate.getTime();
	}
}
