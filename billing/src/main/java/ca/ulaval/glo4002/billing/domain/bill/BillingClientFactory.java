package ca.ulaval.glo4002.billing.domain.bill;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
	private LocalDateTime localDateTime;

	private MemorySubmission memBill = new MemorySubmission();
	private DateManager dateManager = new DateManager();

	@JsonCreator
	public BillingClientFactory(@PathParam("id") long id) throws Exception {
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
		else if (dueTerm == DueTerm.DAYS30) {
			localDateTime = LocalDateTime.from(this.effectiveDate.toInstant()).plusDays(30);
			Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
			this.expectedPaiement = Date.from(instant);
		} else if (dueTerm == DueTerm.DAYS90) {
			localDateTime = LocalDateTime.from(this.effectiveDate.toInstant()).plusDays(90);
			Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
			this.expectedPaiement = Date.from(instant);
		}
	}

	private void setDueTerm(DueTerm dueTerm) {
		if (dueTerm == null)
			this.dueTerm = DueTerm.IMMEDIATE;
		else
			this.dueTerm = dueTerm;
	}

	@GET
	@Path("/bills/{id}")
	public BillingClientDto proccessing() throws Exception {
		if (errorStack.empty()) {
			Submission submission = memBill.getSubmissionbyID(id);
			return new BillingClientDto(submission.getId(), effectiveDate, expectedPaiement, this.dueTerm);
		} else {
			throw new Exception("Error(s) found");
		}
	}

	public ErrorStack errorReport() {
		return this.errorStack;
	}

	public Object wayOutFactory() {
		try {
			return this.proccessing();
		} catch (Exception ex) {
			return this.errorReport();
		}
	}
}
