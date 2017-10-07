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

import ca.ulaval.glo4002.billing.domain.submission.Submission;
import ca.ulaval.glo4002.billing.domain.submission.billFactory;;

@Path("/bills")
public class BillResource {

	private billFactory billFactory;

	public BillResource() throws IOException {
		this.billFactory = new billFactory();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object GetBill(String jsonRequest) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Submission submission = mapper.readValue(jsonRequest, Submission.class);
		submission.action(billFactory);
		return billFactory.createBill();
	}
}
