package ca.ulaval.glo4002.billing.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ca.ulaval.glo4002.billing.domain.clients.ClientId;
import ca.ulaval.glo4002.billing.domain.transactions.TransactionType;
import ca.ulaval.glo4002.billing.domain.transactions.TypeOperation;

@JsonIgnoreProperties(value = { "links" }, ignoreUnknown = true)
public class TransactionDto {

  private Date date;
  private TransactionType transactionType;
  private ClientId clientId;
  private TypeOperation typeOperation;
  private float amount;
  private float balance;

  public TransactionDto(Date date, TransactionType transactionType, ClientId clientId, TypeOperation typeOperation,
      float amount, float balance) {
    super();
    this.date = date;
    this.transactionType = transactionType;
    this.clientId = clientId;
    this.typeOperation = typeOperation;
    this.amount = amount;
    this.balance = balance;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public ClientId getClientId() {
    return clientId;
  }

  public void setClientId(ClientId clientId) {
    this.clientId = clientId;
  }

  public TypeOperation getTypeOperation() {
    return typeOperation;
  }

  public void setTypeOperation(TypeOperation typeOperation) {
    this.typeOperation = typeOperation;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  @Override
  public String toString() {
    return "TransactionDto [date=" + date + ", transactionType=" + transactionType + ", clientId=" + clientId
        + ", typeOperation=" + typeOperation + ", amount=" + amount + ", balance=" + balance + "]";
  }

}
