package ca.ulaval.glo4002.billing.application;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.submission.Submission;
import ca.ulaval.glo4002.billing.domain.submission.SubmissionFactory;

public class BillService {
	private ClientService clientService;
	private ProductService productService;

	public BillService() {
		clientService = new ClientService();
		productService = new ProductService();
	}

	public void setParameterBillFactory(String jsonRequest, SubmissionFactory billFactory)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Submission submission = mapper.readValue(jsonRequest, Submission.class);
		submission.action(clientService, productService, billFactory);
	}

}
