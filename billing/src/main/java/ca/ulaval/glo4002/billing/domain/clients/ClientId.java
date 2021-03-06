package ca.ulaval.glo4002.billing.domain.clients;

import javax.persistence.Embeddable;

@Embeddable
public class ClientId {
  private long clientId;

  public ClientId() {
    // Hibernate default constructor. Do not call.
  }

  public ClientId(long id) {
    this.clientId = id;
  }

  public String toString() {
    return Long.toString(clientId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ClientId clientId = (ClientId) o;

    return this.clientId == clientId.clientId;
  }

  @Override
  public int hashCode() {
    return (int) (clientId ^ (clientId >>> Integer.SIZE));
  }
}
