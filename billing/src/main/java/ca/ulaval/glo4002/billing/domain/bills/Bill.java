package ca.ulaval.glo4002.billing.domain.bills;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.products.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity(name = "Bill")
public class Bill {
  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
  private BillId billId;
  @Column(name = "creationDate")
  private Date creationDate;
  @Enumerated(EnumType.STRING)
  private DueTerm dueTerm;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "billId")
  private List<Product> products;

  @JoinColumn(name = "CLIENT_ID", unique = true)
  @OneToOne(cascade = CascadeType.ALL)
  private Client client;

  public Bill() {
  }

  public Bill(BillId billId, Client client, Date creationDate, DueTerm dueTerm, List<Product> products) {
    this.billId = billId;
    this.client = client;
    this.creationDate = creationDate;
    this.dueTerm = dueTerm;
    this.products = products;
  }

  public BigDecimal getTotal() {
    BigDecimal total = new BigDecimal(0);
    for (Product product : products) {
      BigDecimal quantity = new BigDecimal(product.getQuantity());

      BigDecimal subTotal = quantity.multiply(product.getUnitPrice());
      total = total.add(subTotal);
    }
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
}
