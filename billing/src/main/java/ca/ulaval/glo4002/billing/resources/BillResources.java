package ca.ulaval.glo4002.billing.bills;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/bills")
public class BillResources {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void GetBill(String response) {
		System.out.println(response);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root;
		String id = "0";
		try {
			root = mapper.readTree(response);
			id = mapper.writeValueAsString(root.path("clientId"));
		} catch (IOException e) {
			System.out.println("Not json");
			e.printStackTrace();
		}

		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target("http://localhost:8080/clients/" + id);
		javax.ws.rs.client.Invocation.Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);

		Response _response = request.get();
		System.out.println(_response);
	}
}
