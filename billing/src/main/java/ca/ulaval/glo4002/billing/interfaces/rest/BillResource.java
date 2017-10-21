package ca.ulaval.glo4002.billing.interfaces.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.BillDto;

@Path("/bills")
public class BillResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BillDto create(OrderDto bill) {
		BillService billService = new BillService();
		BillDto response = billService.create(bill);
		return response;
	}

}
