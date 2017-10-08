package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.client.Client;
import ca.ulaval.glo4002.errorManager.ErrorClientNotFound;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class MemoryClients {

	private static ArrayList<Client> listClients = new ArrayList<Client>();

	public static void saveClient(Client client) {
		listClients.add(client);
	}

	public static void checkClientExists(long clientId, ErrorStack errorList) {
		try {
			getClientbyID(clientId);
		} catch (Exception ex) {
			errorList.addError(new ErrorClientNotFound(clientId));
		}
	}

	public static Client getClientbyID(long id) throws Exception {

		for (ca.ulaval.glo4002.billing.domain.client.Client client : listClients) {
			if (client.getId() == id) {
				return client;
			}
		}
		throw new Exception("Client " + id + " not found");
	}
}
