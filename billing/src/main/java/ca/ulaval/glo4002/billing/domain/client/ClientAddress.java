package ca.ulaval.glo4002.billing.domain.client;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ClientAddress {
  @JsonSerialize
  private String city;
  @JsonSerialize
  private String country;
  @JsonSerialize
  private String number;
  @JsonSerialize
  private String postalCode;
  @JsonSerialize
  private String province;
  @JsonSerialize
  private String street;
  @JsonSerialize
  private String unit;

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public String getNumber() {
    return number;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getProvince() {
    return province;
  }

  public String getStreet() {
    return street;
  }

  public String getUnit() {
    return unit;
  }
}
