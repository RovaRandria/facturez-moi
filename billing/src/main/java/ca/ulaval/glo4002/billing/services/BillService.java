package ca.ulaval.glo4002.billing.services;

import java.math.BigDecimal;
import java.util.List;

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
import ca.ulaval.glo4002.billing.exceptions.NegativeException;
import ca.ulaval.glo4002.billing.factory.BillDtoFactory;
import ca.ulaval.glo4002.billing.factory.BillFactory;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.CrmProductRepository;
import ca.ulaval.glo4002.billing.repository.InMemoryBillRepository;

public class BillService {
	ClientRepository clientRepository;
	ProductRepository productRepository;
	BillRepository billRepository;
	BillFactory billFactory;
	BillDtoFactory billDtoFactory;

	public BillService() {
		this.clientRepository = new CrmClientRepository();
		this.productRepository = new CrmProductRepository();
		this.billRepository = new InMemoryBillRepository();
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
	}

	public BillDto create(OrderDto order) {
		BillDto billDto = null;

		if (clientExists(order.getClientId()) && eachProductsExist(order.getProductDtos())) {
			if (!hasNegativeValues(order)) {
				Bill bill = billFactory.createBill(order);
				billRepository.saveBill(bill);
				billDto = billDtoFactory.createBillDto(bill);
			}
		}
		return billDto;
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

	public DueTerm useClientDueTerm(ClientId clientId) {
		return clientRepository.getClient(clientId).getDefaultTerm();
	}

	public DueTerm chooseDueTerm(OrderDto order) {
		DueTerm dueTerm;
		if (dueTermIsValid(order.getDueTerm())) {
			dueTerm = order.getDueTerm();
		} else {
			dueTerm = useClientDueTerm(order.getClientId());
		}
		return dueTerm;
	}

	public boolean clientExists(ClientId clientId) {
		Client client = clientRepository.getClient(clientId);
		return client != null;
	}

	public boolean productExists(ProductId productId) {
		Product product = productRepository.getProduct(productId);
		return product != null;
	}

	public boolean eachProductsExist(List<ProductDto> productDtos) {
		boolean eachProductsExist = true;
		for (ProductDto productDto : productDtos) {
			if (!productExists(productDto.getProductId())) {
				eachProductsExist = false;
			}
		}
		return eachProductsExist;
	}
}
