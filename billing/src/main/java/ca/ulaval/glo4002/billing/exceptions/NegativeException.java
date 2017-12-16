package ca.ulaval.glo4002.billing.exceptions;

public class NegativeException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "is negative";
  private final String description;
  private final String entity;

  public NegativeException(String variable, String value) {
    super();
    this.description = variable + " " + ERROR + " : " + value;
    this.entity = variable;
  }

  public String getError() {
    return ERROR;
  }

  public String getDescription() {
    return description;
  }

  public String getEntity() {
    return entity;
  }

}
