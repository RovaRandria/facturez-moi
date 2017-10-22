package ca.ulaval.glo4002.billing.domain.products;

import java.math.BigDecimal;

public class CrmProduct {
	private ProductId id;
	private String name;
	private BigDecimal unitPrice;

	public CrmProduct(ProductId id) {
		this.id = id;
		this.name = "";
		this.unitPrice = new BigDecimal(0);
	}

	public CrmProduct(ProductId id, String name, BigDecimal unitPrice) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	public ProductId getProductId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
}
