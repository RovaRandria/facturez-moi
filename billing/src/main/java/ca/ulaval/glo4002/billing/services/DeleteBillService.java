package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.exceptions.NotFoundException;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;

public class DeleteBillService extends BillingService {

	private BillRepository billRepository;

	public DeleteBillService() {
		prepareDatabase();
		this.billRepository = new HibernateBillRepository();
	}

	public DeleteBillService(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	public void delete(BillId billId) {
		Bill bill = billRepository.find(billId);
		if (bill == null) {
			throw new NotFoundException(Bill.class.getSimpleName(), billId.toString());
		}
		billRepository.delete(bill);
	}
}
