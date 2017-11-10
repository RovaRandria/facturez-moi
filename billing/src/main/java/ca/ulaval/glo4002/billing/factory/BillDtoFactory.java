package ca.ulaval.glo4002.billing.factory;

import java.math.BigDecimal;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.products.CrmProduct;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;

public class BillDtoFactory {

	public static final String BILLS_PATH = "/bills/";

	public BillDto createBillDto(Bill bill) {
		BillDto billDto;
		List<CrmProduct> products = bill.getProductDtos();
		billDto = new BillDto(bill.getBillId().toString(), getTotal(products), bill.getDueTerm(),
				BILLS_PATH + bill.getBillId().toString());
		return billDto;
	}

	public BigDecimal getTotal(List<CrmProduct> products) {
		BigDecimal total = new BigDecimal(0);
		for (CrmProduct product : products) {
			BigDecimal quantity = new BigDecimal(product.getQuantity());
			BigDecimal subTotal = quantity.multiply(product.getUnitPrice());
			total = total.add(subTotal);
		}
		return total;
	}

}
