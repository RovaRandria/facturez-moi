package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.exceptions.NegativeException;

public class BillDtoFactory {

	public static final String BILLS_PATH = "/bills/";

	public BillDto createBillDto(Bill bill) {
		BillDto billDto;

		if (bill.getTotal().signum() < 0) {
			throw new NegativeException(Bill.class.getSimpleName(), bill.getTotal().toString());
		}

		billDto = new BillDto(bill.getBillId().toString(), bill.getTotal(), bill.getDueTerm(),
				BILLS_PATH + bill.getBillId().toString());
		return billDto;
	}

}
