package ca.ulaval.glo4002.billing.domain.bills;

import ca.ulaval.glo4002.billing.domain.Item;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.clients.ClientID;

import java.util.Date;
import java.util.List;

public class Bill {
    BillID billId;
    ClientID clientId;
    Date creationDate;
    DueTerm dueTerm;
    List<Item> items;

    public Bill(BillID billId, ClientID clientId, Date creationDate, DueTerm dueTerm, List<Item> items) {
        this.clientId = clientId;
        this.creationDate = creationDate;
        this.dueTerm = dueTerm;
        this.items = items;
    }

    public BillID getBillId() {
        return billId;
    }

    public ClientID getClientId() {
        return clientId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public DueTerm getDueTerm() {
        return dueTerm;
    }

    public List<Item> getItems() {
        return items;
    }
}
