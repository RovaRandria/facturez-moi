package ca.ulaval.glo4002.billing.repository;

import java.util.concurrent.atomic.AtomicLong;

import ca.ulaval.glo4002.billing.domain.bills.BillId;

public class BillIdGenerator {

  private static BillIdGenerator billIdGeneratorInstance = new BillIdGenerator();
  private static AtomicLong id = new AtomicLong();

  private BillIdGenerator() {
  }

  public BillId getId() {
    return new BillId(id.incrementAndGet());
  }

  public static BillIdGenerator getInstance() {
    return billIdGeneratorInstance;
  }

}
