package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

	public BillDto(long id, BigDecimal total, DueTerm dueTerm) {
		this.id = id;
		this.total = total;
		this.dueTerm = dueTerm;
		this.url = "/bills/" + this.id;
	}

	public boolean equals(BillDto billDto) {
		return (this.id == billDto.id && this.total.doubleValue() == billDto.total.doubleValue()
				&& this.dueTerm == billDto.dueTerm && this.url.equals(billDto.url));
	}
}
