package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;

public class BillDto {

	@JsonSerialize
	private long id;
	@JsonSerialize
	private BigDecimal total;
	@JsonSerialize
	private DueTerm dueTerm;
	@JsonSerialize
	private String url;

	public BillDto(IdBill indice, BigDecimal total, DueTerm dueTerm) {
		this.id = indice.next();
		this.total = total;
		this.dueTerm = dueTerm;
		this.url = "/bills/" + this.id;
	}
}
