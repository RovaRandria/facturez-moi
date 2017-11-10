package ca.ulaval.glo4002.billing.dto;

public class ExceptionDto {
	private String error;
	private String description;
	private String entity;

	public ExceptionDto() {

	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
}
