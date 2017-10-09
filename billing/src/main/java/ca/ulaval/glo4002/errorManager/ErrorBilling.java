package ca.ulaval.glo4002.errorManager;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ErrorBilling {
	@JsonSerialize
	private String error;
	@JsonSerialize
	private String description;
	@JsonSerialize
	private String entity;

	public ErrorBilling(String error, String description, String entity) {
		this.error = error;
		this.description = description;
		this.entity = entity;
	}

	public boolean equals(ErrorBilling errorBilling) {
		return (errorBilling.error.equals(this.error) && errorBilling.description.equals(this.description)
				&& errorBilling.entity.equals(this.entity));
	}
}
