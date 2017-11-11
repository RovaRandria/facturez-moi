package ca.ulaval.glo4002.billing.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;
import ca.ulaval.glo4002.billing.exceptions.NegativeException;
import ca.ulaval.glo4002.billing.factory.BillDtoFactory;
import ca.ulaval.glo4002.billing.factory.BillFactory;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.CrmProductRepository;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;

public class BillService {
	ClientRepository clientRepository;
	ProductRepository productRepository;
	BillRepository billRepository;
	BillFactory billFactory;
	BillDtoFactory billDtoFactory;

	public BillService() {
		prepareDatabase();
		this.clientRepository = new CrmClientRepository();
		this.productRepository = new CrmProductRepository();
		// this.billRepository = new InMemoryBillRepository();
		this.billRepository = new HibernateBillRepository();
		this.billFactory = new BillFactory();
		this.billDtoFactory = new BillDtoFactory();
	}

	public BillService(ClientRepository clientRepository, ProductRepository productRepository,
			BillRepository billRepository) {
		this.clientRepository = clientRepository;
		this.productRepository = productRepository;
		this.billRepository = billRepository;
		this.billFactory = new BillFactory();
		this.billDtoFactory = new BillDtoFactory();
		// We do not call prepareDatabase() here since this constructor is for tests
		// only.
	}

	private void prepareDatabase() {
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.getFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManagerProvider.setEntityManager(entityManager);
	}

	public BillDto create(OrderDto order) {
		BillDto billDto = null;
		if (orderIsValid(order)) {
			Bill bill = billFactory.createBill(order);
			billRepository.saveBill(bill);
			billDto = billDtoFactory.createBillDto(bill);
		}
		return billDto;
	}

	public boolean orderIsValid(OrderDto order) {
		boolean orderIsValid = false;
		Client client = getClient(order.getClientId());
		if (clientExists(client) && eachProductsExist(order.getProductDtos())) {
			if (!hasNegativeValues(order)) {
				order.setDueTerm(chooseDueTerm(client.getDefaultTerm(), order.getDueTerm()));
				orderIsValid = true;
			}
		}
		return orderIsValid;
	}

	public boolean hasNegativeValues(OrderDto order) {
		BigDecimal total = new BigDecimal(0);
		List<ProductDto> listeProducts = order.getProductDtos();
		for (ProductDto productDto : listeProducts) {
			total = total.add(productDto.getPrice());
			if (productDto.getQuantity() < 0) {
				throw new NegativeException("Quantity", "" + productDto.getQuantity());
			}
		}
		if (total.signum() < 0) {
			throw new NegativeException("Total", "" + total.toString());
		}
		return false;
	}

	public boolean dueTermIsValid(DueTerm dueTerm) {
		return dueTerm != null;
	}

	public DueTerm chooseDueTerm(DueTerm clientDueTerm, DueTerm orderDueTerm) {
		DueTerm dueTerm;
		if (dueTermIsValid(orderDueTerm)) {
			dueTerm = orderDueTerm;
		} else {
			dueTerm = clientDueTerm;
		}
		return dueTerm;
	}

	public Client getClient(ClientId clientId) {
		return clientRepository.getClient(clientId);
	}

	public boolean clientExists(Client client) {
		return client != null;
	}

	public Product getProduct(ProductId productId) {
		return productRepository.getProduct(productId);
	}

	public boolean productExists(Product product) {
		return product != null;
	}

	public boolean eachProductsExist(List<ProductDto> productDtos) {
		boolean eachProductsExist = true;
		for (ProductDto productDto : productDtos) {
			Product product = getProduct(productDto.getProductId());
			if (!productExists(product)) {
				eachProductsExist = false;
			}
		}
		return eachProductsExist;
	}
}
