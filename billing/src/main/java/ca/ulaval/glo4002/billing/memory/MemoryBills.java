package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.dto.BillingClientDto;

public class MemoryBills {
	private static ArrayList<BillingClientDto> listBills = new ArrayList<BillingClientDto>();

	public static void saveBill(BillingClientDto bill) {
		listBills.add(bill);
	}

	public static BillingClientDto getBillByID(long id) throws Exception {
		for (BillingClientDto bill : listBills) {
			if (bill.getId() == id) {
				return bill;
			}
		}
		throw new Exception("bill " + id + " not found");
	}

	public static boolean BillExists(long id) {
		boolean billExist = false;
		try {
			getBillByID(id);
			billExist = true;
		} catch (Exception ex) {
			billExist = false;
		}
		return billExist;
	}
}
