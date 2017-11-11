package ca.ulaval.glo4002.billing.services;

import java.util.Calendar;
import java.util.Date;

import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;

public class InvoiceService {

	private InvoiceRepository invoiceRepository;
	private BillRepository billRepository;

	public InvoiceService() {
		invoiceRepository = new HibernateInvoiceRepository();
		billRepository = new HibernateBillRepository();

	}

	public InvoiceService(InvoiceRepository invoiceRepository, BillRepository billRepository) {
		this.invoiceRepository = invoiceRepository;
		this.billRepository = billRepository;
	}

	public Invoice createInvoice(BillId billId) {
		Bill bill = billRepository.find(billId);
		Date dueDate = calculateDueDate(bill);
		Invoice invoice = new Invoice(dueDate);
		return invoice;
	}

	public boolean invoiceExists(BillId billId) {
		boolean billExists = false;

		Invoice invoice = invoiceRepository.find(billId);

		if (invoice != null) {
			billExists = true;
		}

		return billExists;
	}

	public Date calculateDueDate(Bill bill) {
		Calendar dueDate = Calendar.getInstance();
		int daysToAdd = DueTerm.convertToInt(bill.getDueTerm());
		dueDate.setTime(bill.getCreationDate());
		dueDate.add(Calendar.DATE, daysToAdd);
		return dueDate.getTime();
	}
}
