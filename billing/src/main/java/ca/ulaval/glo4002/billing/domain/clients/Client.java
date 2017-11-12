package ca.ulaval.glo4002.billing.domain.clients;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Client")
public class Client {

  @Id
  @EmbeddedId
  @GeneratedValue(strategy = GenerationType.AUTO)
  private ClientId id;
  // private ClientCategory category;
  @Column(name = "creationDate")
  private String creationDate;
  @Enumerated(EnumType.STRING)
  private DueTerm defaultTerm;
  @Column(name = "fullName")
  private String fullName;
  @Column(name = "email")
  private String email;
  // private ClientAddress address;

  public Client() {
  }

  public Client(ClientId id) {
    this.id = id;
    // this.category = null;
    this.creationDate = null;
    this.defaultTerm = null;
    this.fullName = "";
    this.email = "";
    // this.address = null;
  }

  public Client(ClientId id, ClientCategory category, String creationDate, DueTerm defaultTerm, String fullName,
      String email, ClientAddress address) {
    this.id = id;
    // this.category = category;
    this.creationDate = creationDate;
    this.defaultTerm = defaultTerm;
    this.fullName = fullName;
    this.email = email;
    // this.address = address;
  }

  public ClientId getId() {
    return id;
  }

  /*
   * public ClientCategory getCategory() { return category; }
   */

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

  /*
   * public ClientAddress getAddress() { return address; }
   */

}
