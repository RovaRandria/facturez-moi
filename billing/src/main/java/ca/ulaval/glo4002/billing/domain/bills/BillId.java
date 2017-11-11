package ca.ulaval.glo4002.billing.domain.bills;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class BillId implements Serializable {
	private long billId;

	public BillId(long id) {
		this.billId = id;
	}

	@Override
	public String toString() {
		return Long.toString(billId);
	}

	public long getId() {
		return billId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BillId billId = (BillId) o;

		return this.billId == billId.billId;
	}

	@Override
	public int hashCode() {
		return (int) (billId ^ (billId >>> 32));
	}
}
