package ca.ulaval.glo4002.billing.interfaces.rest;

import ca.ulaval.glo4002.billing.application.ClientService;
import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.submission.BillFactory;
import ca.ulaval.glo4002.billing.memory.MemoryClients;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/bills")
public class BillResource {

  public static ClientService clientService;
  public static ProductService productService;
  public static MemoryClients memoryClients;
  public static MemoryProduct memoryProduct;

  public BillResource() throws IOException {
    this.memoryClients = new MemoryClients();
    this.memoryProduct = new MemoryProduct();
    this.clientService = new ClientService(memoryClients);
    this.productService = new ProductService(memoryProduct);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Object getBill(String jsonRequest) throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    BillFactory billFactory = mapper.readValue(jsonRequest, BillFactory.class);
    return billFactory.wayOutFactory();
  }
}
