package ca.ulaval.glo4002.billing.domain.products;

import java.math.BigDecimal;

public class CrmProduct {
	private ProductId id;
	private String name;
	private BigDecimal unitPrice;
	private int quantity;

	public CrmProduct(ProductId id) {
		this.id = id;
		this.name = "";
		this.unitPrice = new BigDecimal(0);
		this.quantity = 0;
	}

	public CrmProduct(ProductId id, String name, BigDecimal unitPrice, int quantity) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }
}
