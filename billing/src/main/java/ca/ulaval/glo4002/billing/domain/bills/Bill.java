package ca.ulaval.glo4002.billing.domain.bills;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;

public class Bill {
	BillId billId;
	ClientId clientId;
	Date creationDate;
	DueTerm dueTerm;
	List<Product> products;

	public Bill(BillId billId, ClientId clientId, Date creationDate, DueTerm dueTerm, List<Product> products) {
		this.billId = billId;
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.dueTerm = dueTerm;
		this.products = products;
	}

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(0);
		for (Product product : products) {
			BigDecimal quantity = new BigDecimal(product.getQuantity());

			BigDecimal subTotal = quantity.multiply(product.getUnitPrice());
			total = total.add(subTotal);
		}
		return total;
	}

	public BillId getBillId() {
		return billId;
	}

	public ClientId getClientId() {
		return clientId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public DueTerm getDueTerm() {
		return dueTerm;
	}

	public List<Product> getProductDtos() {
		return products;
	}
}
