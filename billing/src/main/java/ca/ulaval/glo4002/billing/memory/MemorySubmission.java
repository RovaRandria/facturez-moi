package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.submission.Submission;

public class MemorySubmission {

	private static ArrayList<Submission> listBill = new ArrayList<Submission>();

	public void saveSubmissions(Submission submission) {
		listBill.add(submission);
	}

	public static Submission getSubmissionbyID(long id) throws Exception {
		for (Submission submission : listBill) {
			if (submission.getId() == id) {
				return submission;
			}
		}
		throw new Exception("Submission " + id + " not found");
	}

	public static boolean submissionExists(long id) {
		boolean submissionExist = false;
		try {
			getSubmissionbyID(id);
			submissionExist = true;
		} catch (Exception ex) {
			submissionExist = false;
		}
		return submissionExist;
	}
}
