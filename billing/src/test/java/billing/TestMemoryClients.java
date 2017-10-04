package billing;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import ca.ulaval.glo4002.billing.memory.MemoryClients;

public class TestMemoryClients {

	private MemoryClients memoryClients;

	@Before
	public void init() {
		memoryClients = new MemoryClients();
	}

	@Test
	public void whenSavingClients_thenEveryClientsReturnedByGetClientsAreCorrect() {
		Random rand = new Random();
		int nbOfClients = rand.nextInt(10 - 1 + 1) + 1;
		ArrayList<String> array = new ArrayList<>();

		for (int i = 0; i < nbOfClients; i++) {
			array.add("client" + i);
			memoryClients.saveClient("client" + i);
		}

		ArrayList<String> memoryList = memoryClients.getClients();

		Assert.assertEquals(array, memoryList);
	}
}
