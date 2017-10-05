package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.bill.Submission;

public class MemoryBill {

	private ArrayList<Submission> listBill = new ArrayList<Submission>();

	public void saveBill(Submission bill) {
		listBill.add(bill);
	}

}
