package ca.ulaval.glo4002.billing.domain.invoices;

import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity(name = "Invoice")
public class Invoice {
  @Id
  @Embedded
  private InvoiceId id;
  @Column(name = "effectiveDate")
  private Date effectiveDate;
  @Enumerated(EnumType.STRING)
  private DueTerm dueTerm;

  public Invoice() {
  }

  public Invoice(InvoiceId id, Date effectiveDate, DueTerm dueTerm) {
    this.id = id;
    this.effectiveDate = effectiveDate;
    this.dueTerm = dueTerm;
  }

  public InvoiceId getId() {
    return this.id;
  }

  public Date getEffectiveDate() {
    return this.effectiveDate;
  }

  public DueTerm getDueTerm() {
    return this.dueTerm;
  }

  public Date getExpectedPayment() {
    Calendar dueDate = Calendar.getInstance();
    int daysToAdd = DueTerm.convertToInt(dueTerm);
    dueDate.setTime(effectiveDate);
    dueDate.add(Calendar.DATE, daysToAdd);
    return dueDate.getTime();
  }
}
