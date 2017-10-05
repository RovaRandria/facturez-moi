package ca.ulaval.glo4002.billing.application;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.domain.submission.Submission;
import ca.ulaval.glo4002.billing.domain.submission.SubmissionFactory;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;

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
		long idClient = submission.getClientId();
		ArrayList<ItemForBill> items = (ArrayList<ItemForBill>) submission.getItems();
		if (clientService.clientExists(idClient)) {
			for (ItemForBill item : items) {
				long idProduct = item.getProductId();
				if (!productService.productExists(idProduct)) {
					billFactory
							.addErrorsObject(new Error("not found", "product " + idProduct + " not found", "product"));
				}
			}
			billFactory.configureBill(submission);
		} else {
			billFactory.addErrorsObject(new Error("not found", "client " + idClient + " not found", "client"));
		}
	}

}
