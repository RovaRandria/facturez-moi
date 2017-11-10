package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

public class BillDto {

	protected final String id;
	protected final BigDecimal total;
	protected final DueTerm dueTerm;
	protected final String url;

	public BillDto(String billId, BigDecimal total, DueTerm dueTerm, String url) {
		this.id = billId;
		this.total = total;
		this.dueTerm = dueTerm;
		this.url = url;
	}

	public String getId() {
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
