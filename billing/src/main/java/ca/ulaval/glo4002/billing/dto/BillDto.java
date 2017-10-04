package ca.ulaval.glo4002.billing.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.dto.DueTerm;

public class BillDto {

	@JsonSerialize
	private long id;
	@JsonSerialize
	private BigDecimal total;
	@JsonSerialize
	private DueTerm dueTerm;
	@JsonSerialize
	private String url;

	public BillDto(long id, BigDecimal total, DueTerm dueTerm) {
		this.id = id;
		this.total = total;
		this.dueTerm = dueTerm;
		this.url = "/bills/" + id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setDueTerm(DueTerm dueTerm) {
		this.dueTerm = dueTerm;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
