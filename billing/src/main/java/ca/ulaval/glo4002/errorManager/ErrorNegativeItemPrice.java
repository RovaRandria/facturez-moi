package ca.ulaval.glo4002.errorManager;

public class ErrorNegativeItemPrice extends ErrorService {

	public ErrorNegativeItemPrice(double d) {
		super("negative item price", "item price " + d + " invalid", "item");
	}
}
