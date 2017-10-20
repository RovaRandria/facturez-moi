package ca.ulaval.glo4002.billing.interfaces.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.dto.RequestBillDto;
import ca.ulaval.glo4002.billing.dto.ResponseBillDto;

@Path("/bills")
public class BillResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseBillDto create(RequestBillDto bill) {
		BillService billService = new BillService();
		ResponseBillDto response = billService.create(bill);
		return response;
	}

}
