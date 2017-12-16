package ca.ulaval.glo4002.billing.domain.clients;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Client")
public class Client {

  // We created this ID because we wasted too much time trying to use ClientId as
  // the primary key... we were having a key violation and we didn't find a quick
  // way to fix it.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Embedded
  @Column(name = "CLIENT_ID")
  private ClientId clientId;

  @Column(name = "creationDate")
  private String creationDate;

  @Enumerated(EnumType.STRING)
  private DueTerm defaultTerm;

  @Column(name = "fullName")
  private String fullName;

  @Column(name = "email")
  private String email;

  public Client() {
  }

  public Client(ClientId id) {
    this.clientId = id;
    this.creationDate = null;
    this.defaultTerm = null;
    this.fullName = "";
    this.email = "";
  }

  public Client(ClientId id, String creationDate, DueTerm defaultTerm, String fullName, String email) {
    this.clientId = id;
    this.creationDate = creationDate;
    this.defaultTerm = defaultTerm;
    this.fullName = fullName;
    this.email = email;
  }

  public ClientId getClientId() {
    return clientId;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public DueTerm getDefaultTerm() {
    return defaultTerm;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

}
