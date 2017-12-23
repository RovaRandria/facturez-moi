package ca.ulaval.glo4002.billing.services;

import java.util.Date;

import ca.ulaval.glo4002.billing.assembler.InvoiceAssembler;
import ca.ulaval.glo4002.billing.domain.accounts.Account;
import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.accounts.AccountRepository;
import ca.ulaval.glo4002.billing.domain.bills.Bill;
import ca.ulaval.glo4002.billing.domain.bills.BillId;
import ca.ulaval.glo4002.billing.domain.bills.BillRepository;
import ca.ulaval.glo4002.billing.domain.invoices.Invoice;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceId;
import ca.ulaval.glo4002.billing.domain.invoices.InvoiceRepository;
import ca.ulaval.glo4002.billing.domain.transactions.Transaction;
import ca.ulaval.glo4002.billing.domain.transactions.TransactionRepository;
import ca.ulaval.glo4002.billing.domain.transactions.TransactionType;
import ca.ulaval.glo4002.billing.domain.transactions.TypeOperation;
import ca.ulaval.glo4002.billing.dto.InvoiceDto;
import ca.ulaval.glo4002.billing.exceptions.InvoiceNotFoundException;
import ca.ulaval.glo4002.billing.factory.InvoiceFactory;
import ca.ulaval.glo4002.billing.repository.HibernateAccountRepository;
import ca.ulaval.glo4002.billing.repository.HibernateBillRepository;
import ca.ulaval.glo4002.billing.repository.HibernateInvoiceRepository;
import ca.ulaval.glo4002.billing.repository.HibernateTransactionRepository;

public class InvoiceService extends BillingService {

  private InvoiceRepository invoiceRepository;
  private BillRepository billRepository;
  private InvoiceFactory invoiceFactory;
  private InvoiceAssembler invoiceDtoFactory;
  private AccountRepository accountRepository;
  private TransactionRepository transactionRepository;

  private static final AccountId ACCOUNT_ID = new AccountId(0);

  public InvoiceService() {
    prepareDatabase();
    this.invoiceRepository = new HibernateInvoiceRepository();
    this.billRepository = new HibernateBillRepository();
    this.invoiceFactory = new InvoiceFactory();
    this.invoiceDtoFactory = new InvoiceAssembler();
    this.accountRepository = new HibernateAccountRepository();
    this.transactionRepository = new HibernateTransactionRepository();
  }

  public InvoiceService(InvoiceRepository invoiceRepository, BillRepository billRepository,
      AccountRepository accountRepository, TransactionRepository transactionRepository) {
    this.invoiceRepository = invoiceRepository;
    this.billRepository = billRepository;
    this.invoiceFactory = new InvoiceFactory();
    this.invoiceDtoFactory = new InvoiceAssembler();
    this.accountRepository = accountRepository;
    this.transactionRepository = transactionRepository;
  }

  public InvoiceDto create(BillId billId) {
    InvoiceDto invoiceDto = null;
    Bill bill = billRepository.find(billId);
    if (bill != null) {
      Invoice invoice = invoiceFactory.create(bill);
      InvoiceId invoiceId = invoiceRepository.insert(invoice);
      invoiceDto = invoiceDtoFactory.create(invoiceId, invoice);

      Account account = accountRepository.find(ACCOUNT_ID);
      Transaction transaction = new Transaction(new Date(), TransactionType.INVOICE,
          invoice.getPaidAmount().floatValue(), TypeOperation.CREDIT, invoice.getBill().getClient(), ACCOUNT_ID);
      transactionRepository.insert(transaction);
      account.addTransaction(transaction);
      accountRepository.update(account);

    }

    if (invoiceDto == null) {
      throw new InvoiceNotFoundException();
    }

    return invoiceDto;
  }

  public boolean invoiceExists(InvoiceId invoiceId) {
    boolean invoiceExists = false;
    Invoice invoice = invoiceRepository.find(invoiceId);

    if (invoice != null) {
      invoiceExists = true;
    }

    return invoiceExists;
  }

  public void delete(InvoiceId invoiceId) {
    Invoice invoice = invoiceRepository.find(invoiceId);
    if (invoice == null) {
      throw new InvoiceNotFoundException();
    }
    invoiceRepository.delete(invoice);

    Account account = accountRepository.find(ACCOUNT_ID);
    Transaction transaction = new Transaction(new Date(), TransactionType.INVOICE_CANCELLED,
        invoice.getPaidAmount().floatValue(), TypeOperation.DEBIT, invoice.getBill().getClient(), ACCOUNT_ID);
    transactionRepository.insert(transaction);
    account.addTransaction(transaction);
    accountRepository.update(account);
  }
}
