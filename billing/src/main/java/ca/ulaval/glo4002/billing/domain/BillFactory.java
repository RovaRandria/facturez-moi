package ca.ulaval.glo4002.billing.domain;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;

import ca.ulaval.glo4002.billing.dto.DueTerm;
import itemsManager.Cart;
import memory.MemoryBill;
import memory.MemoryClients;

public class BillFactory {

	private IdBill indice = new IdBill();

	private long idClient = 0;
	private Date date = new Date();
	private BigDecimal total = new BigDecimal(0);
	private DueTerm dueTerm = DueTerm.IMMEDIATE;
	private Cart itemRepository;

	private MemoryBill memBill = new MemoryBill();

	public void setidClient(long idClient) {
		this.idClient = idClient;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setDueTerm(DueTerm dueTerm) {
		this.dueTerm = dueTerm;
	}

	public void setCart(Cart cart) {
		this.total = cart.total;
		this.itemRepository = cart;
	}

	public void setDate(String __date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			this.date = dateFormat.parse(__date);
		} catch (ParseException e) {
			e.printStackTrace();
		} // courtesy to
			// stackoverflow.com/questions/35952809/java-convert-string-in-iso-instant-format-to-date
			// mais il doit exister de meilleures méthodes
	}

	public void configure(JsonNode node, MemoryClients memoryClients) throws IOException {
		setidClient(idClient);
		setDate(node.path("creationDate").asText());
		setDueTerm(DueTerm.getDueTermFromString(node.path("dueTerm").asText()));
		Cart cart = new Cart();
		JsonNode itemNode = node.path("items");
		if (!itemNode.isArray()) {
			// Gerer cas vide
		}
		for (JsonNode _node : itemNode) {
			cart.addItem(_node);
		}
		setCart(cart);
	}

	public BillDto createBill() {
		long id = indice.next();
		if (total == null || dueTerm == null) {
			System.out.println("fdsfd");
		}
		memBill.saveBill(new Bill(id, idClient, date, itemRepository, dueTerm));
		return new BillDto(id, total, dueTerm);
	}

}
