package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;

public class BillDto {

	@JsonSerialize
	protected final long id;
	@JsonSerialize
	protected final BigDecimal total;
	@JsonSerialize
	protected final CrmDueTerm dueTerm;
	@JsonSerialize
	protected final String url;

	public BillDto(BillId billId, BigDecimal total, CrmDueTerm dueTerm, String string) {
		id = billId.getId();
		this.total = total;
		this.dueTerm = dueTerm;
		this.url = string;
	}

	public long getId() {
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
