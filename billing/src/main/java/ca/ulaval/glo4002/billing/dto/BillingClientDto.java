package ca.ulaval.glo4002.billing.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.domain.client.DueTerm;

public class BillingClientDto {

	@JsonSerialize
	private long id;
	@JsonSerialize
	private Date effectiveDate;
	@JsonSerialize
	private Date expectedPaiement;
	@JsonSerialize
	private DueTerm dueTerm;
	@JsonSerialize
	private String url;

	public BillingClientDto(long id, Date effectiveDate, Date expectedPaiement, DueTerm dueTerm) {
		this.id = id;
		this.effectiveDate = effectiveDate;
		this.expectedPaiement = expectedPaiement;
		this.dueTerm = dueTerm;
		this.url = "/bills/" + id;
	}
}
