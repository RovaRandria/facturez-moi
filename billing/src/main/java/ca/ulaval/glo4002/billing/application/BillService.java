package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.BillFactory;

public class BillService {

	private Properties prop;
	private static ObjectMapper mapper;
	private JsonNode node;
	private ClientService clientService;

	public BillService() throws IOException { // FileInputStream throws FileNotFoundException (part of IOException)
		prop = new Properties();
		prop.load(BillService.class.getClassLoader().getResourceAsStream("application.properties"));
		mapper = new ObjectMapper();
		clientService = new ClientService(prop, mapper);
	}

	public void setParameterBillFactory(String response, BillFactory billFactory) throws IOException {
		node = mapper.readTree(response);
		long idClient = node.path("clientId").asLong();
		if (clientService.clientExists(idClient)) {
			billFactory.configure(node, clientService.getDueTerm(idClient, node.path("dueTerm").toString()));
		}
	}

}
