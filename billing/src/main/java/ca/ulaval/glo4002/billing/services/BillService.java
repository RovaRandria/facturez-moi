package ca.ulaval.glo4002.billing.services;

import java.math.BigDecimal;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.Item;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.repository.InMemoryClientRepository;

public class BillService {
	ClientRepository clientRepository;

	public BillService() {
		this.clientRepository = new InMemoryClientRepository();
	}

	public BillService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	public BillDto create(OrderDto order) {
		if (clientExists(order.getClientId())) {

		}
		return null;
	}

	public boolean clientExists(ClientId clientId) {
		CrmClient crmClient = clientRepository.getClient(clientId);
		return crmClient.getClientId().getId() == clientId.getId();
	}

	public BigDecimal getTotal(List<Item> items) {
		BigDecimal total = new BigDecimal(0);
		for (Item item : items) {
			BigDecimal itemTotalPrice = new BigDecimal(item.getPrice() * item.getQuantity());
			total = total.add(itemTotalPrice);
		}
		return total;
	}

	public boolean dueTermIsValid(CrmDueTerm dueTerm) {
        return dueTerm != null;
	}

    public CrmDueTerm useClientDueTerm(ClientId clientId) {
        return clientRepository.getClient(clientId).getDefaultTerm();
    }
}
