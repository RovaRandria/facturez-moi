package ca.ulaval.glo4002.billing.repository;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class InMemoryClientRepository implements ClientRepository {

	@Override
	public CrmClient getClient(ClientId id) {
		Client client = Client.create();
		WebResource resource = client
				.resource(BillingProperties.getInstance().getProperty("crmClientsUrl") + id.getId());
		CrmClient crmClient = resource.type(MediaType.APPLICATION_JSON).get(CrmClient.class);
		return crmClient;
	}

}
