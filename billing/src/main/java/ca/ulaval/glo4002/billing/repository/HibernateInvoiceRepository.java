package ca.ulaval.glo4002.billing.repository;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

public class HibernateInvoiceRepository implements InvoiceRepository {

	private EntityManager entityManager;

	public HibernateInvoiceRepository() {
		this.entityManager = new EntityManagerProvider().getEntityManager();
	}

	@Override
	public Invoice find(BillId billId) {
		return entityManager.find(Invoice.class, billId);
	}

	@Override
	public void insert(Invoice invoice) {
		entityManager.getTransaction().begin();
		entityManager.persist(invoice);
		entityManager.getTransaction().commit();
	}
}
