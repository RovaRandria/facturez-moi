package errorManager;

public class ErrorProductNotFound extends Error {

	public ErrorProductNotFound(long id) {
		super("not found", "product " + id + " not found", "product");
	}
}
