package ca.ulaval.glo4002.billing.exceptions;

public class FileLookupFailedException extends BillingException {

  private static final long serialVersionUID = 1L;
  private static final String ERROR = "IO Error on a file";
  private static String description;
  private static String entity;

  public FileLookupFailedException() {
  }

  public FileLookupFailedException(String entity, String description) {
    super();
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
