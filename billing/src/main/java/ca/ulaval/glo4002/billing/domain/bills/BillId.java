package ca.ulaval.glo4002.billing.domain.bills;

public class BillId {
	private long id;

	public BillId(long id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return Long.toString(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillId billId = (BillId) o;

        return id == billId.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
