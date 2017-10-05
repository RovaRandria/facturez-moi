package ca.ulaval.glo4002.billing.domain.client;

import java.time.Instant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Client {

	@JsonSerialize
	private Integer id;
	@JsonSerialize
	private ClientCategory category;
	@JsonSerialize
	private Instant creationDate;
	@JsonSerialize
	private DueTerm defaultTerm;
	@JsonSerialize
	private String fullName;
	@JsonSerialize
	private String email;
	@JsonSerialize
	private ClientAddress address;

	public Integer getId() {
		return id;
	}

	public ClientCategory getCategory() {
		return category;
	}

	public Instant getCreationDate() {
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
