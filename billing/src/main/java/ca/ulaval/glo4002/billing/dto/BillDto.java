package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;

public class BillDto {

	@JsonSerialize
	protected final BillId id;
	@JsonSerialize
	protected final BigDecimal total;
	@JsonSerialize
	protected final CrmDueTerm dueTerm;
	@JsonSerialize
	protected final String url;

	public BillDto(BillId billId, BigDecimal total, CrmDueTerm dueTerm, String url) {
		id = billId;
		this.total = total;
		this.dueTerm = dueTerm;
		this.url = url;
	}

	public BillId getId() {
		return id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public CrmDueTerm getDueTerm() {
		return dueTerm;
	}

	public String getUrl() {
		return url;
	}

}
