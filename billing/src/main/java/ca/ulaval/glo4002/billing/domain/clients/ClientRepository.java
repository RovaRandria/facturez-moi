package ca.ulaval.glo4002.billing.domain.clients;

public interface ClientRepository {
	CrmClient getClient(ClientId id);
}
