package ca.ulaval.glo4002.billing.domain.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Client {
	@JsonSerialize
	private Integer id;
	@JsonSerialize
	private ClientCategory category;
	@JsonSerialize
	private String creationDate;
	@JsonSerialize
	private DueTerm defaultTerm;
	@JsonSerialize
	private String fullName;
	@JsonSerialize
	private String email;
	@JsonSerialize
	private ClientAddress address;
	@JsonSerialize
	private Object _links;

	@JsonCreator
	public Client(@JsonProperty("id") Integer id, @JsonProperty("category") ClientCategory category,
			@JsonProperty("creationDate") String creationDate, @JsonProperty("defaultTerm") DueTerm defaultTerm,
			@JsonProperty("fullName") String fullName, @JsonProperty("email") String email,
			@JsonProperty("address") ClientAddress address, @JsonProperty("_links") Object _links) {
		this.id = id;
		this.category = category;
		this.creationDate = creationDate;
		this.defaultTerm = defaultTerm;
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this._links = _links;
	}

	public Integer getId() {
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
