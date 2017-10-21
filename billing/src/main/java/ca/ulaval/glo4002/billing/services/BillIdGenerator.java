package ca.ulaval.glo4002.billing.services;

import java.util.concurrent.atomic.AtomicLong;

import ca.ulaval.glo4002.billing.domain.bills.BillId;

public class BillIdGenerator {

	private AtomicLong id;

	public BillIdGenerator() {
		this.id = new AtomicLong();
	}

	public BillId getId() {
		return new BillId(id.incrementAndGet());
	}

}
