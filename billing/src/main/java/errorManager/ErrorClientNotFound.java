package errorManager;

public class ErrorClientNotFound extends Error {

	public ErrorClientNotFound(long id) {
		super("not found", "client " + id + " not found", "client");
	}
}
