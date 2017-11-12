package ca.ulaval.glo4002.billing.repository;

import ca.ulaval.glo4002.billing.domain.payments.Payment;
import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.domain.payments.PaymentRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

import javax.persistence.EntityManager;

public class HibernatePaymentRepository implements PaymentRepository {

  private EntityManager entityManager;

  public HibernatePaymentRepository() {
    this.entityManager = new EntityManagerProvider().getEntityManager();
  }

  @Override
  public PaymentId insert(Payment payment) {
    entityManager.getTransaction().begin();
    entityManager.persist(payment);
    entityManager.getTransaction().commit();
    return payment.getPaymentId();
  }
}
