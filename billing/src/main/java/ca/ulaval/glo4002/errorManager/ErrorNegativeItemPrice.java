package ca.ulaval.glo4002.errorManager;

public class ErrorNegativeItemPrice extends ErrorBilling {

  public ErrorNegativeItemPrice(double d) {
    super("negative item price", "item price " + d + " invalid", "item");
  }
}
