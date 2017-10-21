package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.repository.InMemoryClientRepository;

public class BillService {
	InMemoryClientRepository inMemoryClientRepository;

	public BillService() {
		this.inMemoryClientRepository = new InMemoryClientRepository();
	}

	public BillService(InMemoryClientRepository inMemoryClientRepository) {
		this.inMemoryClientRepository = inMemoryClientRepository;
	}

	public BillDto create(OrderDto order) {
		if (clientExist(order.getClientId())) {

		}
		return null;
	}

	public boolean clientExist(ClientId clientId) {
		CrmClient crmClient = inMemoryClientRepository.getClient(clientId);
		return crmClient.getClientId().getId() == clientId.getId();
	}

}
