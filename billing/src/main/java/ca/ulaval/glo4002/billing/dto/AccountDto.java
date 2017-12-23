package ca.ulaval.glo4002.billing.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDto {

  private long accountId;
  private List<TransactionDto> entries;

  @JsonCreator
  public AccountDto(@JsonProperty("accountId") long accountId, @JsonProperty("entries") List<TransactionDto> entries) {
    this.accountId = accountId;
    this.entries = entries;
  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  public void setEntries(List<TransactionDto> entries) {
    this.entries = entries;
  }

  public List<TransactionDto> getEntries() {
    return entries;
  }
}
