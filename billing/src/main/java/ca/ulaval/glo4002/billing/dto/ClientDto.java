package ca.ulaval.glo4002.billing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

@JsonIgnoreProperties(value = { "_links" }, ignoreUnknown = true)
public class ClientDto {

  private int id;
  private String creationDate;
  private DueTerm defaultTerm;
  private String fullName;
  private String email;

  public String getCreationDate() {
    return creationDate;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public int getId() {
    return id;
  }

  public DueTerm getDefaultTerm() {
    return defaultTerm;
  }
}
