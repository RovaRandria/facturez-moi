package ca.ulaval.glo4002.billing.domain.bills;

import java.util.Date;
import java.util.List;

import ca.ulaval.glo4002.billing.dto.ProductDto;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

public class Bill {
	BillId billId;
	ClientId clientId;
	Date creationDate;
	DueTerm dueTerm;
	List<ProductDto> productDtos;

	public Bill(BillId billId, ClientId clientId, Date creationDate, DueTerm dueTerm, List<ProductDto> productDtos) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.dueTerm = dueTerm;
		this.productDtos = productDtos;
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

	public List<ProductDto> getProductDtos() {
		return productDtos;
	}
}
