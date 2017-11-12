package ca.ulaval.glo4002.billing.domain.bills;

public interface BillRepository {
  public void insert(Bill bill);

  public Bill find(BillId billId);
}
