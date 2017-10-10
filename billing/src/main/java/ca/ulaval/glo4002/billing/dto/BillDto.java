package ca.ulaval.glo4002.billing.dto;

import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

public class BillDto {

  @JsonSerialize
  protected long id;
  @JsonSerialize
  protected BigDecimal total;
  @JsonSerialize
  protected DueTerm dueTerm;
  @JsonSerialize
  protected String url;

  public BillDto(long id, BigDecimal total, DueTerm dueTerm) {
    this.id = id;
    this.total = total;
    this.dueTerm = dueTerm;
    this.url = "/bills/" + this.id;
  }

  public boolean equals(BillDto billDto) {
    return (this.id == billDto.id && this.total.doubleValue() == billDto.total.doubleValue()
            && this.dueTerm == billDto.dueTerm && this.url.equals(billDto.url));
  }
}
