package ca.ulaval.glo4002.errorManager;

public class ErrorNegativeTotal extends ErrorBilling {

	public ErrorNegativeTotal(double d) {
		super("negative total", "total value is " + d, "total");
	}
}
