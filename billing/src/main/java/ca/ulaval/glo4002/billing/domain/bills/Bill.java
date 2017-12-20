package ca.ulaval.glo4002.billing.domain.bills;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;
import ca.ulaval.glo4002.billing.exceptions.NegativeException;

@Entity(name = "Bill")
public class Bill {

  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BillId billId;

  @Column(name = "creationDate")
  private Date creationDate;

  @Enumerated(EnumType.STRING)
  private DueTerm dueTerm;

  @Column(name = "total")
  private BigDecimal total = new BigDecimal(0);

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "billId")
  private List<Product> products;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "CLIENT_ID")
  private Client client;

  private static final String TOTAL_TEXT = "Total";
  private static final String QUANTITY_TEXT = "Quantity";

  public Bill() {
  }

  public Bill(BillId billId, Client client, Date creationDate, DueTerm dueTerm, List<Product> products) {
    this.billId = billId;
    this.client = client;
    this.creationDate = creationDate;
    this.dueTerm = dueTerm;
    this.products = products;
    this.total = calculateTotal();
  }

  public BigDecimal getTotal() {
    return total;
  }

  public BillId getBillId() {
    return billId;
  }

  public Client getClient() {
    return client;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public DueTerm getDueTerm() {
    return dueTerm;
  }

  public List<Product> getProductDtos() {
    return products;
  }

  private BigDecimal calculateTotal() {
    for (Product product : products) {
      BigDecimal quantity = new BigDecimal(product.getQuantity());

      BigDecimal subTotal = quantity.multiply(product.getUnitPrice());
      total = total.add(subTotal);
    }
    return total;
  }

  public boolean ifHasNegativeValuesThenThrowNegativeException() {
    Boolean hasNegativeValues = false;
    BigDecimal total = calculateTotal();

    List<Product> listeProducts = getProductDtos();

    for (Product product : listeProducts) {
      if (product.getQuantity() < 0) {
        hasNegativeValues = true;
        throw new NegativeException(QUANTITY_TEXT, String.valueOf(product.getQuantity()));
      }
    }

    if (total.signum() < 0) {
      hasNegativeValues = true;
      throw new NegativeException(TOTAL_TEXT, total.toString());
    }

    return hasNegativeValues;

  }

}
