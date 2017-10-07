package errorManager;

public class ErrorNegativeTotal extends ErrorService {

	public ErrorNegativeTotal(long id) {
		super("negative total", "total value is" + id, "total");
	}
}
