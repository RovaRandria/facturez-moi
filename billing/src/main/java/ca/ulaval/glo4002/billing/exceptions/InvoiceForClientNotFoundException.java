package ca.ulaval.glo4002.billing.exceptions;

public class InvoiceForClientNotFoundException extends BillingException {
  private static final long serialVersionUID = 1L;
  private final String ERROR = "not found";
  private final String ENTITY = "Invoice";
  private final String ERROR_DESCRIPTION = "No invoice exists for the client";
  private String description;

  public InvoiceForClientNotFoundException(String clientId) {
    this.description = ERROR_DESCRIPTION + " " + clientId;
  }

  @Override
  public String getError() {
    return this.ERROR;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public String getEntity() {
    return this.ENTITY;
  }
}
