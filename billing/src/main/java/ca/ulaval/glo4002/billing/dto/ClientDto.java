package ca.ulaval.glo4002.billing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ca.ulaval.glo4002.billing.domain.clients.ClientAddress;
import ca.ulaval.glo4002.billing.domain.clients.ClientCategory;
import ca.ulaval.glo4002.billing.domain.clients.DueTerm;

@JsonIgnoreProperties(value = { "_links" }, ignoreUnknown = true)
public class ClientDto {
	private int id;
	private ClientCategory category;
	private String creationDate;
	private DueTerm defaultTerm;
	private String fullName;
	private String email;
	private ClientAddress address;

	public ClientDto() {
		// Jackson
	}

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

	public void setId(int id) {
		this.id = id;
	}

	public ClientCategory getCategory() {
		return category;
	}

	public void setCategory(ClientCategory category) {
		this.category = category;
	}

	public DueTerm getDefaultTerm() {
		return defaultTerm;
	}

	public void setDefaultTerm(DueTerm defaultTerm) {
		this.defaultTerm = defaultTerm;
	}

	public ClientAddress getAddress() {
		return address;
	}

	public void setAddress(ClientAddress address) {
		this.address = address;
	}
}
