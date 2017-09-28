package ca.ulaval.glo4002.billing.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.BillDto.DueTerm;

public class BillService {

	private Properties prop;
	private InputStream input;

	private static ObjectMapper mapper;
	private JsonNode node;

	public BillService() throws IOException { // FileInputStream throws FileNotFoundException (part of IOException)
		prop = new Properties();
		input = new FileInputStream("src/main/resources/application.properties");
		prop.load(input);
		mapper = new ObjectMapper();
	}

	public int retrieveClientIDFromJSON(String response) throws IOException {
		node = mapper.readTree(response);
		return Integer.parseInt(mapper.writeValueAsString(node.path("clientId")));
	}

	public String retrieveClientDueTermFromJSON(String response) throws IOException {
		node = mapper.readTree(response);
		return mapper.writeValueAsString(node.path("dueTerm"));
	}

	public String getClientFromID(int id) {
		Client client = Client.create();
		WebResource webResource = client.resource(prop.getProperty("crmClientsUrl").toString() + id);
		return webResource.get(String.class);
	}

	public BillDto.DueTerm getDueTermFromString(String _dueTerm) {
		BillDto.DueTerm dueTerm;
		switch (_dueTerm) {
		case "IMMENDIATE":
			dueTerm = DueTerm.IMMEDIATE;
			break;
		case "DAYS30":
			dueTerm = DueTerm.DAYS30;
			break;
		case "DAYS90":
			dueTerm = DueTerm.DAYS90;
			break;
		default:
			dueTerm = DueTerm.IMMEDIATE;
			break;
		}
		return dueTerm;
	}
}
