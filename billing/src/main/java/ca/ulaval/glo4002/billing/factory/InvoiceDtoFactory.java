package ca.ulaval.glo4002.billing.factory;

import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;

public class InvoiceDtoFactory {

	public static final String BILLS_PATH = "/bills/";

	public InvoiceDto create(Invoice invoice) {
		return new InvoiceDto(invoice.getId().toString(), invoice.getEffectiveDate(), invoice.getExpectedPayment(),
				invoice.getDueTerm(), BILLS_PATH + invoice.getId().toString());
	}
}
