package ca.ulaval.glo4002.billing.services;

import java.math.BigDecimal;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.clients.CrmClient;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;
import ca.ulaval.glo4002.billing.domain.products.CrmProduct;
import ca.ulaval.glo4002.billing.domain.products.ProductId;
import ca.ulaval.glo4002.billing.domain.products.ProductRepository;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.CrmProductRepository;
import ca.ulaval.glo4002.billing.repository.InMemoryBillRepository;

public class BillService {
	ClientRepository clientRepository;
	ProductRepository productRepository;
	BillRepository billRepository;

	public BillService() {
		this.clientRepository = new CrmClientRepository();
		this.productRepository = new CrmProductRepository();
		this.billRepository = new InMemoryBillRepository();
	}

	public BillService(ClientRepository clientRepository, ProductRepository productRepository,
			BillRepository billRepository) {
		this.clientRepository = clientRepository;
		this.productRepository = productRepository;
		this.billRepository = billRepository;
	}

	public BillDto create(OrderDto order) {
		BillDto billDto = null;

		if (clientAndProductsExist(order)) {
			// TODO Replace createBill with a BillFactory
			Bill bill = createBill(order);
			billRepository.saveBill(bill);
			billDto = createBillDto(bill);
		}
		return billDto;
	}

	private BillDto createBillDto(Bill bill) { // A deplacer dans l'eventuel factory
		BillDto billDto;
		List<ProductDto> productDtos = bill.getProductDtos();
		BigDecimal total = new BigDecimal(0);
		for (ProductDto productDto : productDtos) {
			total = total.add(productDto.getPrice());
		}
		billDto = new BillDto(bill.getBillId(), total, bill.getDueTerm(), "/bills/" + bill.getBillId().toString());
		return billDto;
	}

	private Bill createBill(OrderDto order) {
		BillIdGenerator billIdGenerator = BillIdGenerator.getInstance();
		return new Bill(billIdGenerator.getId(), order.getClientId(), order.getDate(), order.getDueTerm(),
				order.getProductDtos());
	}

	public BigDecimal getTotal(List<ProductDto> productDtos) {
		BigDecimal total = new BigDecimal(0);
		for (ProductDto productDto : productDtos) {
			BigDecimal itemTotalPrice = productDto.getPrice().multiply(new BigDecimal(productDto.getQuantity()));
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

	public boolean clientAndProductsExist(OrderDto order) {
		if (clientExists(order.getClientId()) && eachProductsExist(order.getProductDtos())) {
			return true;
		}
		return false;
	}

	private boolean eachProductsExist(List<ProductDto> productDtos) {
		boolean eachProductsExist = true;
		for (ProductDto productDto : productDtos) {
			if (!productExists(productDto.getProductId())) {
				eachProductsExist = false;
			}
		}
		return eachProductsExist;
	}
}
