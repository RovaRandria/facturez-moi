package ca.ulaval.glo4002.billing.repository;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;

import java.util.Hashtable;

public class InMemoryBillRepository implements BillRepository {

  private Hashtable<BillId, Bill> bills;

  public InMemoryBillRepository() {
    bills = new Hashtable<>();
  }

  @Override
  public void insert(Bill bill) {
    if (!bills.containsKey(bill.getBillId())) {
      bills.put(bill.getBillId(), bill);
    }
  }
}
