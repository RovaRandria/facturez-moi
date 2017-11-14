package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
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

}
