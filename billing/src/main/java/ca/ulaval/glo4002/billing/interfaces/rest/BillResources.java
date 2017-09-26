package ca.ulaval.glo4002.billing.interfaces.rest;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.application.BillService;
import ca.ulaval.glo4002.billing.dto.BillDto;;

@Path("/bills")
public class BillResources {
	private BillService billService;

	public BillResources() {
		this.billService = new BillService();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BillDto GetBill(String response) throws IOException {
		int id = billService.retrieveClientIDFromJSON(response);
		String client = billService.getClientFromID(id);

		long billId = 0;
		BigDecimal billTotal = new BigDecimal(10);
		BillDto.DueTerm dueTerm = billService.getDueTermFromString(billService.retrieveClientDueTermFromJSON(client));
		String url = "/bills/" + billId;

		BillDto bill = new BillDto(billId, billTotal, dueTerm, url);
		return bill;
	}
}
