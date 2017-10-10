package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.client.Client;

public class MemoryClients {

	private ArrayList<Client> listClients = new ArrayList<Client>();

	public void saveClient(Client client) {
		listClients.add(client);
	}

	public boolean checkClientID(long id) {
		for (ca.ulaval.glo4002.billing.domain.client.Client client : listClients) {
			if (client.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
