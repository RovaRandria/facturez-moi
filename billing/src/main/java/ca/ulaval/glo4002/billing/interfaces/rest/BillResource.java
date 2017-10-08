package ca.ulaval.glo4002.billing.interfaces.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.application.ClientService;
import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.submission.BillFactory;;

@Path("/bills")
public class BillResource {

	ClientService clientService;
	ProductService productService;

	public BillResource() throws IOException {
		clientService = new ClientService();
		productService = new ProductService();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object GetBill(String jsonRequest) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		BillFactory billFactory = mapper.readValue(jsonRequest, BillFactory.class);
		return billFactory.wayOutFactory();
	}
}
