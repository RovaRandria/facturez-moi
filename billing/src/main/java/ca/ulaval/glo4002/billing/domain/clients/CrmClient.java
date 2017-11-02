package ca.ulaval.glo4002.billing.domain.clients;

import java.time.Instant;

public class CrmClient {

	private ClientId id;
	private CrmClientCategory category;
	private Instant creationDate;
	private CrmDueTerm defaultTerm;
	private String fullName;
	private String email;
	//private CrmClientAddress address;

	public CrmClient(ClientId id) {
		this.id = id;
		this.category = null;
		this.creationDate = null;
		this.defaultTerm = null;
		this.fullName = "";
		this.email = "";
		//this.address = null;
	}

	public CrmClient(ClientId id, CrmClientCategory category, Instant creationDate, CrmDueTerm defaultTerm,
			String fullName, String email, CrmClientAddress address) {
		this.id = id;
		this.category = category;
		this.creationDate = creationDate;
		this.defaultTerm = defaultTerm;
		this.fullName = fullName;
		this.email = email;
		//this.address = address;
	}

	public ClientId getClientId() {
		return id;
	}

	public CrmClientCategory getCategory() {
		return category;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public CrmDueTerm getDefaultTerm() {
		return defaultTerm;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	/*public CrmClientAddress getAddress() {
		return address;
	}*/
}
