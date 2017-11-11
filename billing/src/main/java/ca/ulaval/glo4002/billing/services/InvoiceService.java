package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.factory.InvoiceDtoFactory;
import ca.ulaval.glo4002.billing.factory.InvoiceFactory;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;

public class InvoiceService extends BillingService {

	private InvoiceRepository invoiceRepository;
	private BillRepository billRepository;
	private InvoiceFactory invoiceFactory;
	private InvoiceDtoFactory invoiceDtoFactory;

	public InvoiceService() {
		prepareDatabase();
		this.invoiceRepository = new HibernateInvoiceRepository();
		this.billRepository = new HibernateBillRepository();
		this.invoiceFactory = new InvoiceFactory();
		this.invoiceDtoFactory = new InvoiceDtoFactory();
	}

	public InvoiceService(InvoiceRepository invoiceRepository, BillRepository billRepository) {
		this.invoiceRepository = invoiceRepository;
		this.billRepository = billRepository;
		this.invoiceFactory = new InvoiceFactory();
		this.invoiceDtoFactory = new InvoiceDtoFactory();
	}

	public InvoiceDto create(BillId billId) {
		InvoiceDto invoiceDto = null;
		Bill bill = billRepository.find(billId);
		if (bill != null) {
			Invoice invoice = invoiceFactory.create(bill);
			invoiceRepository.insert(invoice);
			invoiceDto = invoiceDtoFactory.create(invoice);
		}
		return invoiceDto;
	}

	public boolean invoiceExists(BillId billId) {
		boolean billExists = false;

		Invoice invoice = invoiceRepository.find(billId);

		if (invoice != null) {
			billExists = true;
		}

		return billExists;
	}
}
