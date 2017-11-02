package ca.ulaval.glo4002.billing.domain.products;

public class ProductId {
	private int id;

	public ProductId(int id) {
		this.id = id;
	}

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductId productId = (ProductId) o;

        return id == productId.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
