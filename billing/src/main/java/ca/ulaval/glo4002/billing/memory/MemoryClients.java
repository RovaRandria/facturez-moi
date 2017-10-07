package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.client.Client;

public class MemoryClients {

	private static ArrayList<Client> listClients = new ArrayList<Client>();

	public void saveClient(Client client) {
		listClients.add(client);
	}

	public Client getClientbyID(long id) throws Exception {

		for (ca.ulaval.glo4002.billing.domain.client.Client client : listClients) {
			if (client.getId() == id) {
				return client;
			}
		}
		throw new Exception("Client " + id + " not found");
	}
}
