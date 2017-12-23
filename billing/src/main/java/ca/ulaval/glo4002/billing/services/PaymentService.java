package ca.ulaval.glo4002.billing.services;

import java.util.Date;

import ca.ulaval.glo4002.billing.assembler.ReceiptAssembler;
import ca.ulaval.glo4002.billing.domain.accounts.Account;
import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.accounts.AccountRepository;
import ca.ulaval.glo4002.billing.domain.clients.Client;
import ca.ulaval.glo4002.billing.domain.clients.ClientRepository;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.domain.payments.Payment;
import ca.ulaval.glo4002.billing.domain.payments.PaymentId;
import ca.ulaval.glo4002.billing.domain.payments.PaymentRepository;
import ca.ulaval.glo4002.billing.domain.transactions.Transaction;
import ca.ulaval.glo4002.billing.domain.transactions.TransactionType;
import ca.ulaval.glo4002.billing.domain.transactions.TypeOperation;
import ca.ulaval.glo4002.billing.dto.PaymentDto;
import ca.ulaval.glo4002.billing.dto.ReceiptDto;
import ca.ulaval.glo4002.billing.factory.PaymentFactory;
import ca.ulaval.glo4002.billing.repository.CrmClientRepository;
import ca.ulaval.glo4002.billing.repository.HibernateAccountRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernatePaymentRepository;

public class PaymentService extends BillingService {

  private PaymentRepository paymentRepository;
  private InvoiceRepository invoiceRepository;
  private ClientRepository clientRepository;
  private AccountRepository accountRepository;
  private PaymentFactory paymentFactory;
  private ReceiptAssembler receiptDtoFactory;
  private static final AccountId ACCOUNT_ID = new AccountId(0);

  public PaymentService() {
    prepareDatabase();
    this.paymentRepository = new HibernatePaymentRepository();
    this.invoiceRepository = new HibernateInvoiceRepository();
    this.clientRepository = new CrmClientRepository();
    this.paymentFactory = new PaymentFactory();
    this.receiptDtoFactory = new ReceiptAssembler();
    this.accountRepository = new HibernateAccountRepository();
  }

  public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository,
      ClientRepository clientRepository, AccountRepository accountRepository) {
    this.paymentRepository = paymentRepository;
    this.invoiceRepository = invoiceRepository;
    this.clientRepository = clientRepository;
    this.paymentFactory = new PaymentFactory();
    this.receiptDtoFactory = new ReceiptAssembler();
    this.accountRepository = accountRepository;
  }

  public ReceiptDto pay(PaymentDto paymentDto) {
    Client client = clientRepository.getClient(paymentDto.getClientId());
    Invoice invoice = invoiceRepository.findLast(paymentDto.getClientId());
    invoice.addPayment(paymentDto.getAmount());

    Payment payment = paymentFactory.create(paymentDto, client);

    PaymentId paymentId = paymentRepository.insert(payment);

    Account account = accountRepository.find(ACCOUNT_ID);
    account.addTransaction(new Transaction(new Date(), TransactionType.PAYMENT, payment.getAmount(),
        TypeOperation.DEBIT, client, ACCOUNT_ID));
    accountRepository.update(account);

    ReceiptDto receiptDto = receiptDtoFactory.create(paymentId);

    return receiptDto;
  }
}
