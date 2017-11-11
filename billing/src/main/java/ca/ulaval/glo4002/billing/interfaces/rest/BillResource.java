package ca.ulaval.glo4002.billing.interfaces.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.services.BillService;
import ca.ulaval.glo4002.billing.services.InvoiceService;

@Path("/bills")
public class BillResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BillDto create(OrderDto bill) {
		BillService billService = new BillService();
		return billService.create(bill);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public InvoiceDto createInvoice(@PathParam("id") int id) {
		BillId billId = new BillId(id);
		InvoiceService invoiceService = new InvoiceService();
		return invoiceService.create(billId);
	}

}
