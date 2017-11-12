package ca.ulaval.glo4002.billing.interfaces.rest;

import ca.ulaval.glo4002.billing.dto.PaymentDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/payments")
public class PaymentResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pay(PaymentDto paymentDto) {
    return null;
  }
}
