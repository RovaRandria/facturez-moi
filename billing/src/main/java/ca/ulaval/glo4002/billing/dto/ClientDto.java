package ca.ulaval.glo4002.billing.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ca.ulaval.glo4002.billing.domain.clients.CrmClientAddress;
import ca.ulaval.glo4002.billing.domain.clients.CrmClientCategory;
import ca.ulaval.glo4002.billing.domain.clients.CrmDueTerm;

@JsonIgnoreProperties(value = { "_links" }, ignoreUnknown = true)
public class ClientDto {
	private int id;
	private CrmClientCategory category;
	private String creationDate;
	private CrmDueTerm defaultTerm;
	private String fullName;
	private String email;
	private CrmClientAddress address;

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

	public CrmClientCategory getCategory() {
		return category;
	}

	public void setCategory(CrmClientCategory category) {
		this.category = category;
	}

	public CrmDueTerm getDefaultTerm() {
		return defaultTerm;
	}

	public void setDefaultTerm(CrmDueTerm defaultTerm) {
		this.defaultTerm = defaultTerm;
	}

	public CrmClientAddress getAddress() {
		return address;
	}

	public void setAddress(CrmClientAddress address) {
		this.address = address;
	}
}
