package ca.ulaval.glo4002.billing.domain.bill;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;

import ca.ulaval.glo4002.billing.domain.DateManager;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.domain.submission.Submission;
import ca.ulaval.glo4002.billing.dto.BillingClientDto;
import ca.ulaval.glo4002.billing.memory.MemorySubmission;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class BillingClientFactory {

	private long id;
	private Date effectiveDate;
	private Date expectedPaiement;
	private DueTerm dueTerm;
	private ErrorStack errorStack;

	private MemorySubmission memBill = new MemorySubmission();
	private DateManager dateManager = new DateManager();

	@JsonCreator
	public BillingClientFactory() throws Exception {
		this.errorStack = new ErrorStack();
		setId(id);
		setDueTerm(dueTerm);
		this.effectiveDate = dateManager.stringToDate(memBill.getSubmissionbyID(id).getCreationDate());
		setExpectedPaiement(effectiveDate, dueTerm);
	}

	private void setId(long id) {
		this.id = id;
		memBill.checkSubmissionExists(this.id, this.errorStack);
	}

	private void setExpectedPaiement(Date effectiveDate, DueTerm dueTerm) {
		if (dueTerm == DueTerm.IMMEDIATE)
			this.expectedPaiement = this.effectiveDate;
		else if (dueTerm == DueTerm.DAYS30)
			this.expectedPaiement = this.effectiveDate; // + 30;
		else if (dueTerm == DueTerm.DAYS90)
			this.expectedPaiement = this.effectiveDate; // + 90;
	}

	private void setDueTerm(DueTerm dueTerm) {
		if (dueTerm == null)
			this.dueTerm = DueTerm.IMMEDIATE;
		else
			this.dueTerm = dueTerm;
	}

	public BillingClientDto proccessing() throws Exception {
		if (errorStack.empty()) {
			Submission submission = memBill.getSubmissionbyID(id); // parametre id ???
			return new BillingClientDto(submission.getId(), effectiveDate, expectedPaiement, this.dueTerm);
		} else {
			throw new Exception("Error(s) found");
		}
	}
}
