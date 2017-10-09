package ca.ulaval.glo4002.errorManager;

public class ErrorSubmissionAleadyAccepted extends ErrorService {

	public ErrorSubmissionAleadyAccepted(long id) {
		super("wrong status", "Invoice already accepted", "invoice");
	}
}
