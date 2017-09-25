package ca.ulaval.glo4002.billing.resources;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.BillDto.DueTerm;

@Path("/bills")
public class BillResources {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BillDto GetBill(String response) throws IOException {
		int id = retrieveClientIDFromJSON(response);
		String client = getClientFromID(id);

		long billId = 0;
		BigDecimal billTotal = new BigDecimal(10);
		BillDto.DueTerm dueTerm = getDueTermFromString(retrieveClientDueTermFromJSON(response));
		String url = "/bills/" + billId;

		BillDto bill = new BillDto(billId, billTotal, dueTerm, url);
		return bill;
	}

	private int retrieveClientIDFromJSON(String response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response);
		return Integer.parseInt(mapper.writeValueAsString(root.path("clientId")));
	}

	private String retrieveClientDueTermFromJSON(String response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response);
		return mapper.writeValueAsString(root.path("dueTerm"));
	}

	private String getClientFromID(int id) {
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/clients/" + id);
		String clientJSON = webResource.get(String.class);
		return clientJSON;
	}

	private BillDto.DueTerm getDueTermFromString(String _dueTerm) {
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
