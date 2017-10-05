package ca.ulaval.glo4002.billing.domain.bill;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.memory.MemoryBill;

public class BillFactory {

	private IdBill indice = new IdBill();

	private Submission submission;
	private BigDecimal total;

	private MemoryBill memBill = new MemoryBill();

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void configure(Submission submission) {
		this.submission = submission;
	}

	public BillDto createBill() {
		long id = indice.next();
		if (total == null || submission.getDueTerm() == null) {
			System.out.println("fdsfd");
		}
		memBill.saveBill(submission);
		return new BillDto(id, total, DueTerm.DAYS30);
	}

}
