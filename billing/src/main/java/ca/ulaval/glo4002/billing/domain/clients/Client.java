package ca.ulaval.glo4002.billing.domain.clients;

public class Client {

  private ClientId id;
  private ClientCategory category;
  private String creationDate;
  private DueTerm defaultTerm;
  private String fullName;
  private String email;
  private ClientAddress address;

  public Client() {
  }

  public Client(ClientId id) {
    this.id = id;
    this.category = null;
    this.creationDate = null;
    this.defaultTerm = null;
    this.fullName = "";
    this.email = "";
    this.address = null;
  }

  public Client(ClientId id, ClientCategory category, String creationDate, DueTerm defaultTerm, String fullName,
                String email, ClientAddress address) {
    this.id = id;
    this.category = category;
    this.creationDate = creationDate;
    this.defaultTerm = defaultTerm;
    this.fullName = fullName;
    this.email = email;
    this.address = address;
  }

  public ClientId getClientId() {
    return id;
  }

  public ClientCategory getCategory() {
    return category;
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

  public ClientAddress getAddress() {
    return address;
  }

}
