package ca.ulaval.glo4002.billing.interfaces.rest;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.services.BillService;
import ca.ulaval.glo4002.billing.services.InvoiceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/bills")
public class BillResource {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(OrderDto bill) {
    BillService billService = new BillService();
    BillDto billDto = billService.create(bill);
    return Response.status(Response.Status.CREATED).entity(billDto).build();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("{id}")
  public Response createInvoice(@PathParam("id") int id) {
    BillId billId = new BillId(id);
    InvoiceService invoiceService = new InvoiceService();
    InvoiceDto invoiceDto = invoiceService.create(billId);
    return Response.status(Response.Status.OK).entity(invoiceDto).build();
  }

}
