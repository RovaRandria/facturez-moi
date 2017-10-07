package ca.ulaval.glo4002.errorManager;

public class ErrorClientNotFound extends ErrorService {

	public ErrorClientNotFound(long id) {
		super("not found", "client " + id + " not found", "client");
	}
}
