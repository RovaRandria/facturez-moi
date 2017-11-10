package ca.ulaval.glo4002.billing.dto;

import java.math.BigDecimal;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;

public class BillDto {

	protected final String id;
	protected final BigDecimal total;
	protected final CrmDueTerm dueTerm;
	protected final String url;

	public BillDto(String billId, BigDecimal total, CrmDueTerm dueTerm, String url) {
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

	public CrmDueTerm getDueTerm() {
		return dueTerm;
	}

	public String getUrl() {
		return url;
	}

}
