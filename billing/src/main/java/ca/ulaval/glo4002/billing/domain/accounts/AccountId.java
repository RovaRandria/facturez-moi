package ca.ulaval.glo4002.billing.domain.accounts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccountId implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "ACCOUNT_ID")
  private long accountId;

  public AccountId() {
  }

  public AccountId(long id) {
    this.accountId = id;
  }

  @Override
  public String toString() {
    return Long.toString(accountId);
  }

  public long getId() {
    return accountId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AccountId accountId = (AccountId) o;

    return this.accountId == accountId.accountId;
  }

  @Override
  public int hashCode() {
    return (int) (accountId ^ (accountId >>> Integer.SIZE));
  }

}
