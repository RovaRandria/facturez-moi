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
    this.token = token;
    this.date = timestamp;
  }

  public String getToken() {
    return token;
  }

  public long getDate() {
    return date;
  }
}
