package ca.ulaval.glo4002.billing.domain.bills;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.Item;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;

public class Bill {
	BillId billId;
	ClientId clientId;
	Date creationDate;
	CrmDueTerm dueTerm;
	List<Item> items;

	public Bill(BillId billId, ClientId clientId, Date creationDate, CrmDueTerm dueTerm, List<Item> items) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.dueTerm = dueTerm;
		this.items = items;
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

	public List<Item> getItems() {
		return items;
	}
}
