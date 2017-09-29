package memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.Bill;

public class MemoryBill {

	private ArrayList<Bill> listBill = new ArrayList<Bill>();

	public void saveBill(Bill bill) {
		listBill.add(bill);
	}

}
