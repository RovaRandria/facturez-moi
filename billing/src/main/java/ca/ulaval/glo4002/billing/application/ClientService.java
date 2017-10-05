package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.interfaces.Properties;
import ca.ulaval.glo4002.billing.memory.MemoryClients;

public class ClientService {
	private JsonNode node;
	private MemoryClients memoryClients;

	public ClientService() {
		memoryClients = new MemoryClients();
		saveClients();
	}

	public String getDueTerm(long id, String dueTerm) throws IOException {
		// Get defaultDueTerm if dueTerm empty
		/*
		 * if (dueTerm == "") { node = mapper.readTree(getClientByID(id)); return
		 * node.path("defaultTerm").toString(); } else { return dueTerm; }
		 */
		return dueTerm;
	}

	public boolean clientExists(long id) {
		String client = "";
		try {
			client = getClientByID(id);
		} catch (ArrayIndexOutOfBoundsException | IOException ex) {
			return false;
		}
		return client != "";
	}

	private void saveClients() {
		Client client = Client.create();
		WebResource resource = client.resource(Properties.getInstance().getProperty("crmClientsUrl"));
		List<ca.ulaval.glo4002.billing.domain.client.Client> myClient = resource.type("application/json")
				.get(List.class);
		System.out.println(myClient.get(0).getFullName());
	}

	private String getClientByID(long id) throws IOException {
		/*
		 * ArrayList<ca.ulaval.glo4002.billing.domain.client.Client> clients =
		 * memoryClients.getClients(); for (String client : clients) { node =
		 * mapper.readTree(client); if (node.path("id").asLong() == id) { return client;
		 * } }
		 */
		return "";
	}
}
