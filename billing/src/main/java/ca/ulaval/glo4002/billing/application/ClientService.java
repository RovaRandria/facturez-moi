package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.memory.MemoryClients;

public class ClientService {
	private Properties prop;
	private JsonNode node;
	private MemoryClients memoryClients;
	private ObjectMapper mapper;

	public ClientService(Properties prop, ObjectMapper mapper) {
		memoryClients = new MemoryClients();
		this.prop = prop;
		this.mapper = mapper;
		saveClients();
	}

	public String getDueTerm(long id, String dueTerm) throws IOException {
		// Get defaultDueTerm if dueTerm empty
		if (dueTerm == "") {
			node = mapper.readTree(getClientByID(id));
			return node.path("defaultTerm").toString();
		} else {
			return dueTerm;
		}
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
		WebResource resource = client.resource(prop.getProperty("crmClientsUrl"));
		ClientResponse response = resource.type("application/json").get(ClientResponse.class);
		node = mapper.valueToTree(response.toString());
		Consumer<JsonNode> data = (JsonNode jsonNode) -> memoryClients.saveClient(jsonNode.toString());
		node.forEach(data);
	}

	private String getClientByID(long id) throws IOException {
		ArrayList<String> clients = memoryClients.getClients();
		for (String client : clients) {
			node = mapper.readTree(client);
			if (node.path("id").asLong() == id) {
				return client;
			}
		}
		return "";
	}
}
