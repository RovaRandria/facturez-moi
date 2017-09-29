package ca.ulaval.glo4002.billing.interfaces.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ulaval.glo4002.billing.application.BillService;
import ca.ulaval.glo4002.billing.domain.BillDto;
import ca.ulaval.glo4002.billing.domain.BillFactory;
import memory.MemoryClients;;

@Path("/bills")
public class BillResources {

	private BillService billService;
	private BillFactory billFactoryRepository;
	private MemoryClients memoryClients;

	public BillResources() throws IOException {
		this.billService = new BillService();
		this.billFactoryRepository = new BillFactory();
		this.memoryClients = new MemoryClients(); // en fait à ce moment il faudrait récuperer la fameuse liste des
													// clients
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public BillDto GetBill(String response) throws IOException {

		billService.setParameterBillFactory(response, billFactoryRepository, memoryClients);

		return billFactoryRepository.createBill();
	}
}
