package ca.ulaval.glo4002.billing.interfaces.rest;

import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.services.BillService;
import ca.ulaval.glo4002.billing.services.InvoiceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
  @Path("/{id}")
  public InvoiceDto createInvoice(@PathParam("id") int billId) {
    InvoiceService invoiceService = new InvoiceService();
    return invoiceService.createInvoice(billId);
  }

}
