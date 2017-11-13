package ca.ulaval.glo4002.billing.services;

import java.util.List;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.domain.payments.Payment;
import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.domain.payments.PaymentRepository;
import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.dto.ReceiptDto;
import ca.ulaval.glo4002.billing.factory.PaymentFactory;
import ca.ulaval.glo4002.billing.factory.ReceiptDtoFactory;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernatePaymentRepository;

public class PaymentService extends BillingService {

  private PaymentRepository paymentRepository;
  private InvoiceRepository invoiceRepository;
  private ClientRepository clientRepository;
  private PaymentFactory paymentFactory;
  private ReceiptDtoFactory receiptDtoFactory;

  public PaymentService() {
    prepareDatabase();
    this.paymentRepository = new HibernatePaymentRepository();
    this.invoiceRepository = new HibernateInvoiceRepository();
    this.clientRepository = new CrmClientRepository();
    this.paymentFactory = new PaymentFactory();
    this.receiptDtoFactory = new ReceiptDtoFactory();
  }

  public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository,
      ClientRepository clientRepository) {
    this.paymentRepository = paymentRepository;
    this.invoiceRepository = invoiceRepository;
    this.clientRepository = clientRepository;
    this.paymentFactory = new PaymentFactory();
    this.receiptDtoFactory = new ReceiptDtoFactory();
  }

  public ReceiptDto pay(PaymentDto paymentDto) {
    Client client = clientRepository.getClient(paymentDto.getClientId());

    List<Invoice> invoices = invoiceRepository.findInvoices(paymentDto.getClientId());
    if (invoices.isEmpty()) {
      // error ?
    } else {
      invoices.get(0).addPayment(paymentDto.getAmount());
    }

    Payment payment = paymentFactory.create(paymentDto, client);

    PaymentId paymentId = paymentRepository.insert(payment);

    ReceiptDto receiptDto = receiptDtoFactory.create(paymentId);

    return receiptDto;
  }
}
