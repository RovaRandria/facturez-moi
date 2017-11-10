package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.dto.OrderDto;
import ca.ulaval.glo4002.billing.repository.BillIdGenerator;

public class BillFactory {

	public Bill createBill(OrderDto order) {
		BillIdGenerator billIdGenerator = BillIdGenerator.getInstance();
		return new Bill(billIdGenerator.getId(), order.getClientId(), order.getDate(), order.getDueTerm(),
				order.getProductDtos());
	}

}
