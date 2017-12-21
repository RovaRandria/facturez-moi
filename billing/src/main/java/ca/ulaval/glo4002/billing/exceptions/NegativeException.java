package ca.ulaval.glo4002.billing.exceptions;

public class NegativeException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "is negative";
  private final String description;

  public NegativeException(String description, String value) {
    super();
    this.description = description + " " + ERROR + " : " + value;
  }

  @Override
  public String getError() {
    // TODO Auto-generated method stub
    return ERROR;
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return description;
  }

  @Override
  public String getEntity() {
    // TODO Auto-generated method stub
    return null;
  }

}
