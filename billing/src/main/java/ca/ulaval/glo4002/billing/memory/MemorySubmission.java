package ca.ulaval.glo4002.billing.memory;

import ca.ulaval.glo4002.billing.domain.submission.Submission;

import java.util.ArrayList;

public class MemorySubmission {

  private ArrayList<Submission> listBill = new ArrayList<Submission>();

  public void saveSubmissions(Submission submission) {
    listBill.add(submission);
  }

}
