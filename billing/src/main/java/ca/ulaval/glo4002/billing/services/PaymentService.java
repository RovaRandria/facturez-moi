package ca.ulaval.glo4002.billing.services;

import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.domain.payments.Payment;
import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.domain.payments.PaymentMethod;
import ca.ulaval.glo4002.billing.domain.payments.PaymentRepository;
import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.dto.ReceiptDto;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernatePaymentRepository;

public class PaymentService extends BillingService {

  PaymentRepository paymentRepository;
  InvoiceRepository invoiceRepository;
  ClientRepository clientRepository;

  public PaymentService() {
    prepareDatabase();
    this.paymentRepository = new HibernatePaymentRepository();
    this.invoiceRepository = new HibernateInvoiceRepository();
    this.clientRepository = new CrmClientRepository();
  }

  public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository,
      ClientRepository clientRepository) {
    this.paymentRepository = paymentRepository;
    this.invoiceRepository = invoiceRepository;
    this.clientRepository = clientRepository;
  }

  public ReceiptDto pay(PaymentDto paymentDto) {
    Invoice invoice = invoiceRepository.findLast(paymentDto.getClientId());
    Client client = clientRepository.getClient(paymentDto.getClientId());

    PaymentMethod paymentMethod = new PaymentMethod(paymentDto.getPaymentMethod().getAccount(),
        paymentDto.getPaymentMethod().getSource());
    Payment payment = new Payment(client, paymentDto.getAmount(), paymentMethod);
    PaymentId paymentId = paymentRepository.insert(payment);
    ReceiptDto receiptDto = new ReceiptDto(paymentId.toString(), "payments/" + paymentDto.getClientId().toString());
    return receiptDto;
  }
}
