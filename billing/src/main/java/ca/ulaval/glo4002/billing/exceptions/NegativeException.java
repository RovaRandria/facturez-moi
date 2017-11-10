package ca.ulaval.glo4002.billing.exceptions;

public class NegativeException extends BillingException {

	private static final long serialVersionUID = 1L;
	private final String ERROR = "is negative";
	private String description;
	private String entity;

	public NegativeException() {
	}

	public NegativeException(String className, String id) {
		super();
		this.description = className + " " + id + " " + ERROR;
		this.entity = className;
	}

	public String getError() {
		return this.ERROR;
	}

	public String getDescription() {
		return this.description;
	}

	public String getEntity() {
		return this.entity;
	}

}
