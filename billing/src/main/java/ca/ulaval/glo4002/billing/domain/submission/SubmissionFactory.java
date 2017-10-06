package ca.ulaval.glo4002.billing.domain.submission;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.memory.MemoryBill;

public class SubmissionFactory {

	private IdBill indice = new IdBill();

	private Submission submission;
	private BigDecimal total;
	private Object error;

	private MemoryBill memBill = new MemoryBill();

	public void configureBill(Submission submission) {
		this.submission = submission;
		setTotal();
	}

	public void addErrorsObject(ca.ulaval.glo4002.billing.application.Error error) {
		this.error = error;
	}

	private void setTotal() {
		this.total = submission.getTotal();
	}

	public Object createBill() {
		if (error == null) {
			long id = indice.next();
			if (total == null || submission.getDueTerm() == null) {
				System.out.println("Wrong data");
			}
			memBill.saveBill(submission);
			return new BillDto(id, total, submission.getDueTerm());
		} else {
			return error;
		}
	}

}
