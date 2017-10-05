package ca.ulaval.glo4002.billing.domain.bill;

import java.math.BigDecimal;

import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;
import ca.ulaval.glo4002.billing.memory.MemoryBill;

public class BillFactory {

	private IdBill indice = new IdBill();

	private Submission submission;
	private BigDecimal total;

	private MemoryBill memBill = new MemoryBill();

	public void configureBill(Submission submission) {
		this.submission = submission;
		setTotal();
	}

	private void setTotal() {
		for (ItemForBill item : submission.getItems()) {
			total = item.total();
		}
	}

	public BillDto createBill() {
		long id = indice.next();
		if (total == null || submission.getDueTerm() == null) {
			System.out.println("Wrong data");
		}
		memBill.saveBill(submission);
		return new BillDto(id, total, submission.getDueTerm());
	}

}
