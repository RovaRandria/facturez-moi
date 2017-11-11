package ca.ulaval.glo4002.billing.interfaces.rest.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ca.ulaval.glo4002.billing.entitymanager.EntityManagerFactoryProvider;
import ca.ulaval.glo4002.billing.entitymanager.EntityManagerProvider;

public class EntityManagerContextFilter implements Filter {
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void destroy() {
		entityManagerFactory.close();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		EntityManager entityManager = null;

		try {
			entityManager = entityManagerFactory.createEntityManager();
			EntityManagerProvider.setEntityManager(entityManager);
			chain.doFilter(request, response);
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
			EntityManagerProvider.clearEntityManager();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		entityManagerFactory = EntityManagerFactoryProvider.getFactory();
	}

}
