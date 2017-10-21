package ca.ulaval.glo4002.billing.domain.bills;

import java.util.concurrent.atomic.AtomicLong;

public class BillID {
    AtomicLong id;

    public AtomicLong getId() {
        return id;
    }
}
