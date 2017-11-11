package ca.ulaval.glo4002.billing.dto;

import java.util.Date;

import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

public class InvoiceDto {

	private String id;
	private Date effectiveDate;
	private Date expectedPayment;
	private DueTerm dueTerm;
	private String url;

	public InvoiceDto(String id, Date effectiveDate, Date expectedPayment, DueTerm dueTerm, String url) {
		this.id = id;
		this.effectiveDate = effectiveDate;
		this.expectedPayment = expectedPayment;
		this.dueTerm = dueTerm;
		this.url = url;
	}

	public String getId() {
		return this.id;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public Date getExpectedPayment() {
		return this.expectedPayment;
	}

	public DueTerm getDueTerm() {
		return this.dueTerm;
	}

	public String getUrl() {
		return this.url;
	}

}
