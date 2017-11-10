package ca.ulaval.glo4002.billing.domain.bills;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.domain.products.CrmProduct;

public class Bill {
	BillId billId;
	ClientId clientId;
	Date creationDate;
	CrmDueTerm dueTerm;
	List<CrmProduct> products;

	public Bill(BillId billId, ClientId clientId, Date creationDate, CrmDueTerm dueTerm, List<CrmProduct> products) {
		this.billId = billId;
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.dueTerm = dueTerm;
		this.products = products;
	}

	public BillId getBillId() {
		return billId;
	}

	public ClientId getClientId() {
		return clientId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public CrmDueTerm getDueTerm() {
		return dueTerm;
	}

	public List<CrmProduct> getProductDtos() {
		return products;
	}
}
