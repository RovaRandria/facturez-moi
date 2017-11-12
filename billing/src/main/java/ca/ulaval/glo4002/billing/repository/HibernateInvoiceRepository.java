package ca.ulaval.glo4002.billing.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;
import ca.ulaval.glo4002.billing.exceptions.InvoiceAlreadyCreatedException;

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

		try {
			entityManager.getTransaction().commit();
		} catch (RollbackException e) {
			throw new InvoiceAlreadyCreatedException(Invoice.class.getSimpleName());
		}
	}

	@Override
	public Invoice findLast(ClientId clientId) {
		String query = "select i.* from Invoice i inner join Bill b on b.ClientId = i.ClientId where ClientId = ?1 order by i.expectedPayment desc";
		entityManager.getTransaction().begin();
		List<Invoice> invoices = entityManager.createQuery(
				"from Invoice as i inner join i.Bill as b on b.ClientId = i.ClientId where ClientId = ?1 order by i.expectedPayment desc")
				.setParameter(1, clientId.toString()).getResultList();
		entityManager.getTransaction().commit();
		return invoices.get(0);
	}
}
