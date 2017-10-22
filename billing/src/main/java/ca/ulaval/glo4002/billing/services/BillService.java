package ca.ulaval.glo4002.billing.services;

import java.math.BigDecimal;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.Item;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.domain.products.CrmProduct;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.repository.InMemoryClientRepository;
import ca.ulaval.glo4002.billing.repository.InMemoryProductRepository;

public class BillService {
	ClientRepository clientRepository;
	ProductRepository productRepository;

	public BillService() {
		this.clientRepository = new InMemoryClientRepository();
		this.productRepository = new InMemoryProductRepository();
	}

	public BillService(ClientRepository clientRepository, ProductRepository productRepository) {
		this.clientRepository = clientRepository;
		this.productRepository = productRepository;
	}

	public BillDto create(OrderDto order) {
		if (clientAndProductExist(order)) {

		}
		return null;
	}

	public BigDecimal getTotal(List<Item> items) {
		BigDecimal total = new BigDecimal(0);
		for (Item item : items) {
			BigDecimal itemTotalPrice = new BigDecimal(item.getPrice() * item.getQuantity());
			total = total.add(itemTotalPrice);
		}
		return total;
	}

	public boolean dueTermIsValid(CrmDueTerm dueTerm) {
		return dueTerm != null;
	}

	public CrmDueTerm useClientDueTerm(ClientId clientId) {
		return clientRepository.getClient(clientId).getDefaultTerm();
	}

	public CrmDueTerm chooseDueTerm(OrderDto order) {
		CrmDueTerm dueTerm;
		if (dueTermIsValid(order.getDueTerm())) {
			dueTerm = order.getDueTerm();
		} else {
			dueTerm = useClientDueTerm(order.getClientId());
		}
		return dueTerm;
	}

	public boolean clientExists(ClientId clientId) {
		CrmClient client = clientRepository.getClient(clientId);
		return client != null;
	}

	public boolean productExists(ProductId productId) {
		CrmProduct product = productRepository.getProduct(productId);
		return product != null;
	}

	public boolean clientAndProductExist(OrderDto order) {
		if (clientExists(order.getClientId()) && eachProductsExist(order.getItems())) {
			return true;
		}
		return false;
	}

	private boolean eachProductsExist(List<Item> items) {
		boolean eachProductsExist = true;
		for (Item item : items) {
			if (!productExists(item.getProductId())) {
				eachProductsExist = false;
			}
		}
		return eachProductsExist;
	}
}
