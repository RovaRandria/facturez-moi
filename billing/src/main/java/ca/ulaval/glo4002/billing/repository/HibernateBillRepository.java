package ca.ulaval.glo4002.billing.repository;

import javax.persistence.EntityManager;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

public class HibernateBillRepository implements BillRepository {
	EntityManager entityManager;

	public HibernateBillRepository() {
		this.entityManager = new EntityManagerProvider().getEntityManager();
	}

	@Override
	public void insert(Bill bill) {
		entityManager.getTransaction().begin();
		entityManager.persist(bill);
		entityManager.getTransaction().commit();
	}

}
