package billing.dto;

import java.io.IOException;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.dto.ClientDto;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class ClientDtoTest {
	private final String CRM_CLIENTS_URL = "crmClientsUrl";

	@Test
	public void test() {

		ClientId clientId = new ClientId(1);
		Client client = Client.create();
		WebResource resource = client
				.resource(BillingProperties.getInstance().getProperty(CRM_CLIENTS_URL) + clientId.toString());
		ClientResponse response = resource.get(ClientResponse.class);
		String crmClient = response.getEntity(String.class);
		ClientDto clientDto = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			clientDto = objectMapper.readValue(crmClient, ClientDto.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ClientDto entity = response.getEntity(ClientDto.class);

	}
}
