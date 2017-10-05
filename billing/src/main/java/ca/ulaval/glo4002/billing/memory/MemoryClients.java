package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.client.Client;

public class MemoryClients {

	private ArrayList<Client> listClients = new ArrayList<Client>();

	public void saveClient(Client client) {
		listClients.add(client);
	}

	public ArrayList<Client> getClients() {
		return listClients;
	}
}
