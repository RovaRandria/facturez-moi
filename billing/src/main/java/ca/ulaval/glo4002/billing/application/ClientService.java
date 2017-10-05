package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
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
		boolean clientExist = false;
		try {
			getClientByID(id);
			clientExist = true;
		} catch (Exception ex) {
			clientExist = false;
		}
		return clientExist;
	}

	private void saveClients() {
		ObjectMapper mapper = new ObjectMapper();
		Client client = Client.create();
		WebResource resource = client.resource(Properties.getInstance().getProperty("crmClientsUrl"));
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() == 200) {
			String output = response.getEntity(String.class);
			output = output.substring(38, output.length() - 1);
			System.out.println(output);
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
		throw new Exception("Client not found");
	}
}
