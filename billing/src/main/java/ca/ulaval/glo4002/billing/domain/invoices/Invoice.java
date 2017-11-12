package ca.ulaval.glo4002.billing.domain.invoices;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "Invoice")
public class Invoice {
  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
  private InvoiceId id;
  @Column(name = "effectiveDate")
  private Date effectiveDate;
  @Column(name = "expectedPayment")
  private Date expectedPayment;
  @JoinColumn(name = "BILL_ID", unique = true)
  @OneToOne(cascade = CascadeType.ALL)
  private Bill bill;

  public Invoice() {
  }

  public Invoice(Bill bill) {
    this.id = new InvoiceId(0);
    this.effectiveDate = bill.getCreationDate();
    this.bill = bill;
    this.expectedPayment = calculateExpectedPayment();
  }

  public InvoiceId getId() {
    return this.id;
  }

  public Date getEffectiveDate() {
    return this.effectiveDate;
  }

  public DueTerm getDueTerm() {
    return this.bill.getDueTerm();
  }

  public Date getExpectedPayment() {
    return this.expectedPayment;
  }

  public Bill getBill() {
    return this.bill;
  }

  private Date calculateExpectedPayment() {
    Calendar dueDate = Calendar.getInstance();
    int daysToAdd = DueTerm.toInt(bill.getDueTerm());
    dueDate.setTime(effectiveDate);
    dueDate.add(Calendar.DATE, daysToAdd);
    return dueDate.getTime();
  }
}
