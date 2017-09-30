package billing;

import java.util.Properties;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.ulaval.glo4002.billing.application.ClientService;

public class TestClientService {

	@Test
	public void whenNoClientExists_thenReturnClientExistsFalse() {
		ClientService clientService = new ClientService(new Properties(), new ObjectMapper());
	}
}
