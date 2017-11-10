package ca.ulaval.glo4002.billing.services;

import java.math.BigDecimal;
import java.util.List;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.dto.ProductDto;

public class BillDtoFactory {

	public BillDto createBillDto(Bill bill) {
		BillDto billDto;
		List<ProductDto> productDtos = bill.getProductDtos();
		billDto = new BillDto(bill.getBillId(), getTotal(productDtos), bill.getDueTerm(),
				"/bills/" + bill.getBillId().toString());
		return billDto;
	}

	public BigDecimal getTotal(List<ProductDto> productDtos) {
		BigDecimal total = new BigDecimal(0);
		for (ProductDto productDto : productDtos) {
			BigDecimal quantity = new BigDecimal(productDto.getQuantity());
			BigDecimal subTotal = quantity.multiply(productDto.getPrice());
			total = total.add(subTotal);
		}
		return total;
	}

}
