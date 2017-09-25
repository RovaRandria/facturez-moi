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
		setId(id);
		setTotal(total);
		setDueTerm(dueTerm);
		setUrl(url);
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setDueTerm(DueTerm dueTerm) {
		this.dueTerm = dueTerm;
	}

	public DueTerm getDueTerm() {
		return dueTerm;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
