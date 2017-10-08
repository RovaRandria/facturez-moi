package ca.ulaval.glo4002.billing.memory;

import java.util.ArrayList;

import ca.ulaval.glo4002.billing.domain.submission.Submission;

public class MemorySubmission {

	private ArrayList<Submission> listBill = new ArrayList<Submission>();

	public void saveSubmissions(Submission submission) {
		listBill.add(submission);
	}

}