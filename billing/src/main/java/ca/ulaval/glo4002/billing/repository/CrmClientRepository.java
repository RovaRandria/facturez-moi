package ca.ulaval.glo4002.billing.repository;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class CrmClientRepository implements ClientRepository {

	private final String CRM_CLIENTS_URL = "crmClientsUrl";

	@Override
	public CrmClient getClient(ClientId id) {
		Client client = Client.create();
		WebResource resource = client
				.resource(BillingProperties.getInstance().getProperty(CRM_CLIENTS_URL) + id.toString());
		CrmClient crmClient = resource.type(MediaType.APPLICATION_JSON).get(CrmClient.class);
		return crmClient;
	}

}
