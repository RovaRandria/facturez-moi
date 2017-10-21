package ca.ulaval.glo4002.billing.domain.clients;

import java.util.concurrent.atomic.AtomicLong;

public class ClientID {
	AtomicLong id;

	public AtomicLong getId() {
		return id;
	}
}
