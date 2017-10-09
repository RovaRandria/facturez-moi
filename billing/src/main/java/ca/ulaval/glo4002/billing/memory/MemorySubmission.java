package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.submission.Submission;
import ca.ulaval.glo4002.errorManager.ErrorClientNotFound;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class MemorySubmission {

	private static ArrayList<Submission> listBill = new ArrayList<Submission>();

	public void saveSubmissions(Submission submission) {
		listBill.add(submission);
	}

	public void checkSubmissionExists(long id, ErrorStack errorList) {
		try {
			getSubmissionbyID(id);
		} catch (Exception ex) {
			errorList.addError(new ErrorClientNotFound(id));
		}
	}

	public static boolean submissionExists(long id) {
		boolean submissionExist = false;
		try {
			for (Submission submission : listBill) {
				if (submission.getId() == id) {
					submissionExist = true;
				}
			}
		} catch (Exception ex) {
			submissionExist = false;
		}
		return submissionExist;
	}

	public Submission getSubmissionbyID(long id) throws Exception {
		for (Submission submission : listBill) {
			if (submission.getId() == id) {
				return submission;
			}
		}
		throw new Exception("Submission " + id + " not found");
	}

	public static boolean submissionAlreadyAccepted(long id) throws Exception {
		for (Submission submission : listBill) {
			// traiter le cas d'une soumission déjà accepter
		}
		throw new Exception("Invoice already accepted");
	}
}
