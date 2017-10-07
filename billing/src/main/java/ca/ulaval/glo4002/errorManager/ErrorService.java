package ca.ulaval.glo4002.errorManager;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ErrorService {
	@JsonSerialize
	private String error;
	@JsonSerialize
	private String description;
	@JsonSerialize
	private String entity;

	public ErrorService(String error, String description, String entity) {
		this.error = error;
		this.description = description;
		this.entity = entity;
	}
}
