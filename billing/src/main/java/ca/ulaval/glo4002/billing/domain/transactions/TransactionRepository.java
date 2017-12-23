package ca.ulaval.glo4002.billing.domain.transactions;

public interface TransactionRepository {

  void insert(Transaction transaction);

  Transaction find(int transactionID);
}
