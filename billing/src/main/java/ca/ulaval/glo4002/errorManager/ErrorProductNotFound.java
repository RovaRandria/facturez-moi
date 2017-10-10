package ca.ulaval.glo4002.errorManager;

public class ErrorProductNotFound extends ErrorBilling {

  public ErrorProductNotFound(long id) {
    super("not found", "product " + id + " not found", "product");
  }
}
