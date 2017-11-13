package ca.ulaval.glo4002.billing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;
import ca.ulaval.glo4002.billing.exceptions.InvoiceAlreadyCreatedException;
import ca.ulaval.glo4002.billing.exceptions.NotFoundException;

public class HibernateInvoiceRepository implements InvoiceRepository {

  private EntityManager entityManager;

  public HibernateInvoiceRepository() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  @Override
  public Invoice find(InvoiceId invoiceId) {
    return entityManager.find(Invoice.class, invoiceId);
  }

  @Override
  public InvoiceId insert(Invoice invoice) {
    entityManager.getTransaction().begin();
    entityManager.persist(invoice);

    try {
      entityManager.getTransaction().commit();
    } catch (RollbackException e) {
      throw new InvoiceAlreadyCreatedException(Invoice.class.getSimpleName());
    }
    return invoice.getId();
  }

  @Override
  public Invoice findLast(ClientId clientId) {
    entityManager.getTransaction().begin();
    Session s = (Session) entityManager.getDelegate();
    Criteria criteria = s.createCriteria(Invoice.class, "invoice");
    criteria.createAlias("invoice.bill", "bill");
    criteria.createAlias("bill.client", "client");
    criteria.createAlias("bill.products", "product");
    criteria.add(Restrictions.eq("client.clientId", clientId));
    criteria.add(Restrictions.sqlRestriction("paidAmount <> total"));
    criteria.addOrder(Order.asc("invoice.expectedPayment"));

    List<Invoice> invoices = criteria.list();
    if (invoices.size() < 1) {
      throw new NotFoundException(Client.class.getSimpleName(), clientId.toString());
    } else {
      entityManager.getTransaction().commit();
      return invoices.get(0);
    }
  }

}
