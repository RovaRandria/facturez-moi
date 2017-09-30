package memory;

import java.util.ArrayList;

public class MemoryClients {

	private ArrayList<String> listClients = new ArrayList<String>();

	public void saveClient(String clientJson) {
		listClients.add(clientJson);
	}

	public ArrayList<String> getClients() {
		return listClients;
	}
}
