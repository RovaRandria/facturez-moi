package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BillDto {
	public enum DueTerm {
		IMMEDIATE("IMMEDIATE"), DAYS30("DAYS30"), DAYS90("DAYS90");

		private final String name;

		private DueTerm(String _name) {
			this.name = _name;
		}

		public String toString() {
			return this.name;
		}
	}

	@JsonSerialize
	private long id;
	@JsonSerialize
	private BigDecimal total;
	@JsonSerialize
	private DueTerm dueTerm;
	@JsonSerialize
	private String url;

	public BillDto() {
	}

	public BillDto(long id, BigDecimal total, DueTerm dueTerm, String url) {
		this.id = id;
		this.total = total;
		this.dueTerm = dueTerm;
		this.url = url;
	}

	public long getId() {
		return id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public DueTerm getDueTerm() {
		return dueTerm;
	}

	public String getUrl() {
		return url;
	}
}
