package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.domain.payments.PaymentRepository;
import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.dto.ReceiptDto;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernatePaymentRepository;

public class PaymentService extends BillingService {

	PaymentRepository paymentRepository;
	InvoiceRepository invoiceRepository;

	public PaymentService() {
		prepareDatabase();
		this.paymentRepository = new HibernatePaymentRepository();
		this.invoiceRepository = new HibernateInvoiceRepository();
	}

	public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
		this.paymentRepository = paymentRepository;
		this.invoiceRepository = invoiceRepository;
	}

	public ReceiptDto pay(PaymentDto paymentDto) {
		invoiceRepository.findLast(paymentDto.getClientId());
		ReceiptDto receiptDto = new ReceiptDto();
		return receiptDto;
	}
}
