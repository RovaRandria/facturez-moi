package ca.ulaval.glo4002.billing.domain.submission;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.memory.MemoryBill;
import errorManager.ErrorStack;

public class billFactory {

	private IdBill indice = new IdBill();

	private Submission submission;
	private BigDecimal total;
	private ErrorStack errorList;

	private MemoryBill memBill = new MemoryBill();

	public void configureBill(Submission submission) {
		this.submission = submission;
		this.total = submission.total();
	}

	public void addErrorsObject(errorManager.ErrorService error) {
		if (errorList == null)
			this.errorList = new ErrorStack();
		this.errorList.addError(error);
	}

	public Object createBill() {
		if (errorList == null) {
			long id = indice.next();
			if (total == null || submission.getDueTerm() == null) {
				System.out.println("Wrong data");
			}
			memBill.saveBill(submission);
			return new BillDto(id, total, submission.getDueTerm());
		} else {
			return this.errorList;
		}
	}

}
