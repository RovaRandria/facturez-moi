package ca.ulaval.glo4002.billing.domain.products;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId implements Serializable {
	private int productId;

	public ProductId(int id) {
		this.productId = id;
	}

	@Override
	public String toString() {
		return Integer.toString(productId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProductId productId = (ProductId) o;

		return this.productId == productId.productId;
	}

	@Override
	public int hashCode() {
		return productId;
	}
}
