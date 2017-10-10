package ca.ulaval.glo4002.billing.domain.submission;

import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.itemsManager.Cart;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Submission {

  @JsonSerialize
  private long clientId;
  @JsonSerialize
  private String creationDate;
  @JsonSerialize
  private Cart items;
  @JsonSerialize
  private DueTerm dueTerm;

	public Submission(long clientId, String creationDate, DueTerm dueTerm, Cart items) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.items = items;
		this.dueTerm = dueTerm;
	}

	public long getId() {
		return clientId;
	}

	public String getCreationDate() {
		return creationDate;
	}
}
