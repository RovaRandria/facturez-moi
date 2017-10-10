package ca.ulaval.glo4002.billing.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class HeartbeatDto {

  @JsonSerialize
  private long date;
  @JsonSerialize
  private String token;

  public HeartbeatDto() {
  }

  public HeartbeatDto(long timestamp, String token) {
    setToken(token);
    setDate(timestamp);
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setDate(long date) {
    this.date = date;
  }
}
