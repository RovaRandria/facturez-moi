package ca.ulaval.glo4002.billing.application;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.submission.Submission;
import ca.ulaval.glo4002.billing.domain.submission.SubmissionFactory;

public class BillService {
	private ClientService clientService;

	public BillService() {
		clientService = new ClientService();
	}

	public void setParameterBillFactory(String jsonRequest, SubmissionFactory billFactory)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Submission submission = mapper.readValue(jsonRequest, Submission.class);
		long idClient = submission.getClientId();
		if (clientService.clientExists(idClient)) {
			billFactory.configureBill(submission);
		} else {
			billFactory.addErrorsObject(new Error("not found", "client " + idClient + " not found", "client"));
		}
	}

}
