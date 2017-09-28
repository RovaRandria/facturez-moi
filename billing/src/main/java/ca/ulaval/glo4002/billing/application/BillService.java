package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.BillFactoryRepository;
import ca.ulaval.glo4002.billing.dto.DueTerm;

public class BillService {

	private Properties prop;
	private static ObjectMapper mapper;
	private JsonNode node;

	public BillService() throws IOException { // FileInputStream throws FileNotFoundException (part of IOException)
		prop = new Properties();
		prop.load(BillService.class.getClassLoader().getResourceAsStream("application.properties"));
		mapper = new ObjectMapper();
	}

	// public long retrieveClientIDFromJSON(String response) throws IOException {
	// node = mapper.readTree(response);
	// return Long.parseLong(mapper.writeValueAsString(node.path("clientId")));
	// }
	//
	// public String retrieveClientDueTermFromJSON(String response) throws
	// IOException {
	// node = mapper.readTree(response);
	// return mapper.writeValueAsString(node.path("dueTerm"));
	// }
	//
	// public String getClientFromID(long id) {
	// Client client = Client.create();
	// WebResource webResource =
	// client.resource(prop.getProperty("crmClientsUrl").toString() + id);
	// return webResource.get(String.class);
	// }

	public void setParameterBillFactory(String response) throws IOException {
		node = mapper.readTree(response);
		BillFactoryRepository billFactoryRepository = new BillFactoryRepository();
		billFactoryRepository.setidClient(Long.parseLong(mapper.writeValueAsString(node.path("clientId"))));
		billFactoryRepository.setTotal(new BigDecimal(12)); // A modifier pour calculer le cout
		billFactoryRepository.setDueTerm(DueTerm.getDueTermFromString(mapper.writeValueAsString(node.path("dueTerm"))));
	}

}
