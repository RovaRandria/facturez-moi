package ca.ulaval.glo4002.billing.repository;

import java.io.IOException;
import java.net.URL;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.dto.ClientDto;
import ca.ulaval.glo4002.billing.exceptions.NotFoundException;
import ca.ulaval.glo4002.billing.interfaces.BillingProperties;

public class CrmClientRepository implements ClientRepository {

  private ObjectMapper objectMapper;
  private static final String CRM_CLIENTS_URL = "crmClientsUrl";

  public CrmClientRepository() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  @Override
  public Client getClient(ClientId id) {
    ClientDto clientDto = null;
    try {
      URL url = new URL(BillingProperties.getInstance().getProperty(CRM_CLIENTS_URL) + id.toString());

      clientDto = objectMapper.readValue(url, ClientDto.class);
    } catch (IOException e) {
      throw new NotFoundException(Client.class.getSimpleName(), id.toString());
    }
    return new Client(id, clientDto.getCreationDate(), clientDto.getDefaultTerm(), clientDto.getFullName(),
        clientDto.getEmail());
  }

}
