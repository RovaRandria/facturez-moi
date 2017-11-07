package ca.ulaval.glo4002.billing.repository;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.dto.ClientDto;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class CrmClientRepository implements ClientRepository {

	private final String CRM_CLIENTS_URL = "crmClientsUrl";

	@Override
	public CrmClient getClient(ClientId id) {
		try {
			URL url = new URL(BillingProperties.getInstance().getProperty(CRM_CLIENTS_URL) + id.toString());

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			ClientDto clientDto = objectMapper.readValue(url, ClientDto.class);
			return new CrmClient(id, clientDto.getCategory(), clientDto.getCreationDate(), clientDto.getDefaultTerm(),
					clientDto.getFullName(), clientDto.getEmail(), clientDto.getAddress());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return new CrmClient(id);
	}

}
