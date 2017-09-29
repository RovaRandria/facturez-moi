package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.BillFactory;
import memory.MemoryClients;

public class BillService {

	private Properties prop;
	private static ObjectMapper mapper;
	private JsonNode node;

	public BillService() throws IOException { // FileInputStream throws FileNotFoundException (part of IOException)
		prop = new Properties();
		prop.load(BillService.class.getClassLoader().getResourceAsStream("application.properties"));
		mapper = new ObjectMapper();
	}

	public void setParameterBillFactory(String response, BillFactory billFactory, MemoryClients memoryClients)
			throws IOException { // faudra déplacer ce pan de code dans BillFactory car ce code est lié au
									// contexte de BillFactory
		node = mapper.readTree(response);
		long idClient = node.path("clientId").asLong();
		memoryClients.checkClient(idClient); // Completer memoryClient pour US1 VC2 et demander comment on connait les
												// clients initialement
		billFactory.configure(node, memoryClients);

	}

}
