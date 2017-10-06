package errorManager;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Error {
	@JsonSerialize
	private String error;
	@JsonSerialize
	private String description;
	@JsonSerialize
	private String entity;

	public Error(String error, String description, String entity) {
		this.error = error;
		this.description = description;
		this.entity = entity;
	}
}
