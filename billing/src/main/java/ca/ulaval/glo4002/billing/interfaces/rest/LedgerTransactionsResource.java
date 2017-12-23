package ca.ulaval.glo4002.billing.interfaces.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ca.ulaval.glo4002.billing.dto.AccountDto;
import ca.ulaval.glo4002.billing.services.AccountService;

@Path("/ledger/transactions")
public class LedgerTransactionsResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAccountEntries(@QueryParam("startMonth") String startMonth,
      @QueryParam("endMonth") String endMonth, @QueryParam("year") String year) {
    AccountService accountService = new AccountService();

    AccountDto accountDto = null;

    if (startMonth == null || endMonth == null) {
      accountDto = accountService.retrieveEntries(0, 0, Integer.parseInt(year));
    } else {
      accountDto = accountService.retrieveEntries(Integer.parseInt(startMonth), Integer.parseInt(endMonth),
          Integer.parseInt(year));
    }
    return Response.status(200).entity(accountDto).build();

  }
}