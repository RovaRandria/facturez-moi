package ca.ulaval.glo4002.billing.domain.accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import ca.ulaval.glo4002.billing.domain.transactions.Transaction;
import ca.ulaval.glo4002.billing.domain.transactions.TypeOperation;

@Entity(name = "Account")
public class Account {
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private AccountId accountId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "accountId")
  private List<Transaction> entries = new ArrayList<Transaction>();

  private float balance;

  public Account() {
    this.balance = 0;
  }

  public Account(AccountId accountId) {
    this.accountId = accountId;
    this.balance = 0;
  }

  public AccountId getAccountId() {
    return accountId;
  }

  public List<Transaction> getEntries() {
    return entries;
  }

  public boolean addTransaction(Transaction transaction) {
    if (transaction.getTypeOperation().equals(TypeOperation.CREDIT)) {
      balance += transaction.getAmount();
      transaction.setBalance(balance);
      entries.add(transaction);
      return true;
    }

    else if (transaction.getTypeOperation().equals(TypeOperation.DEBIT)) {
      balance -= transaction.getAmount();
      transaction.setBalance(balance);
      entries.add(transaction);
      return true;
    }

    else {
      return false;
    }

  }

  public List<Transaction> getEntriesForGivenYear(int year) {
    List<Transaction> annualEntries = new ArrayList<Transaction>();
    Calendar calendar = Calendar.getInstance();
    for (Transaction transaction : entries) {
      calendar.setTime(transaction.getDate());
      if (calendar.get(Calendar.YEAR) == year) {
        annualEntries.add(transaction);
      }
    }

    return annualEntries;
  }

  public List<Transaction> getEntriesForGivenPeriodOfYear(int startMonth, int endMonth, int year) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, startMonth, 1);
    Date startDate = calendar.getTime();
    calendar.set(year, endMonth, Calendar.getInstance().getActualMaximum(endMonth));
    Date endDate = calendar.getTime();

    List<Transaction> annualEntries = new ArrayList<Transaction>();
    for (Transaction transaction : entries) {
      Date transactionDate = transaction.getDate();
      if (transactionDate.after(startDate) && transactionDate.before(endDate)) {
        annualEntries.add(transaction);
      }
    }

    return annualEntries;
  }

}
