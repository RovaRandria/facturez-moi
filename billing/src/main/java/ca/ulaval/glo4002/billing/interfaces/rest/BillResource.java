package ca.ulaval.glo4002.billing.interfaces.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.application.BillService;
import ca.ulaval.glo4002.billing.domain.submission.SubmissionFactory;;

@Path("/bills")
public class BillResource {

	private BillService billService;
	private SubmissionFactory billFactoryRepository;

	public BillResource() throws IOException {
		this.billService = new BillService();
		this.billFactoryRepository = new SubmissionFactory();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object GetBill(String jsonRequest) throws IOException {
		billService.setParameterBillFactory(jsonRequest, billFactoryRepository);

		return billFactoryRepository.createBill();
	}
}
