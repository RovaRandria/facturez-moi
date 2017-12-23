package ca.ulaval.glo4002.billing.domain.transactions;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ca.ulaval.glo4002.billing.domain.accounts.AccountId;
import ca.ulaval.glo4002.billing.domain.clients.Client;

@Entity(name = "Transaction")
public class Transaction {

  @Id
  @Column(name = "transactionId")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int transactionId;

  private Date date;

  @Enumerated(EnumType.STRING)
  private TransactionType transactionType;

  private float amount;

  private float balance;

  private AccountId accountId;

  @Enumerated(EnumType.STRING)
  private TypeOperation typeOperation;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "CLIENT_ID")
  private Client client;

  public Transaction() {

  }

  public Transaction(Date date, TransactionType transactionType, float amount, TypeOperation typeOperation,
      Client client, AccountId accountId) {
    this.date = date;
    this.transactionType = transactionType;
    this.amount = amount;
    this.typeOperation = typeOperation;
    this.client = client;
    this.accountId = accountId;
  }

  public Transaction(Date date, TransactionType transactionType, float amount, TypeOperation typeOperation,
      Client client, AccountId accountId, float balance) {
    this.date = date;
    this.transactionType = transactionType;
    this.amount = amount;
    this.balance = balance;
    this.accountId = accountId;
    this.typeOperation = typeOperation;
    this.client = client;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public float getAmount() {
    return amount;
  }

  public Date getDate() {
    return date;
  }

  public TypeOperation getTypeOperation() {
    return typeOperation;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public float getBalance() {
    return balance;
  }

  public Client getClient() {
    return client;
  }

}
