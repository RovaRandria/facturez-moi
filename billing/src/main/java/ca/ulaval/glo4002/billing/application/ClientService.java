package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.domain.submission.BillFactory;
import ca.ulaval.glo4002.billing.interfaces.Properties;
import ca.ulaval.glo4002.billing.memory.MemoryClients;
import ca.ulaval.glo4002.errorManager.ErrorClientNotFound;

public class ClientService {
	private MemoryClients memoryClients;

	public ClientService() {
		memoryClients = new MemoryClients();
		saveClients();
	}

	private void saveClients() {
		ObjectMapper mapper = new ObjectMapper();
		Client client = Client.create();
		WebResource resource = client.resource(Properties.getInstance().getProperty("crmClientsUrl"));
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() == 200) { // magic number
			String output = response.getEntity(String.class);
			String[] outputSplit = output.split("\"clients\" : ");
			output = outputSplit[1].substring(0, outputSplit[1].length() - 1);
			try {
				JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class,
						ca.ulaval.glo4002.billing.domain.client.Client.class);
				ArrayList<ca.ulaval.glo4002.billing.domain.client.Client> clients = mapper.readValue(output, type);
				for (int i = 0; i < clients.size(); i++) {
					memoryClients.saveClient(clients.get(i));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private ca.ulaval.glo4002.billing.domain.client.Client getClientByID(long id) throws Exception {
		ArrayList<ca.ulaval.glo4002.billing.domain.client.Client> clients = memoryClients.getClients();

		for (ca.ulaval.glo4002.billing.domain.client.Client client : clients) {
			if (client.getId() == id) {
				return client;
			}
		}
		throw new Exception("Client " + id + " not found");
	}

	public void checkClientExists(long clientId, BillFactory billFactory) {
		try {
			getClientByID(clientId);
		} catch (Exception ex) {
			billFactory.sendError(new ErrorClientNotFound(clientId));
		}
	}
}
