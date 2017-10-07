package errorManager;

public class ErrorNegativeItemPrice extends ErrorService {

	public ErrorNegativeItemPrice(long id) {
		super("negative item price", "item " + id + " invalid", "item");
	}
}
