package ca.ulaval.glo4002.billing.domain.submission;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ca.ulaval.glo4002.billing.domain.DateManager;
import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.itemsManager.Cart;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;
import ca.ulaval.glo4002.billing.memory.MemoryClients;
import ca.ulaval.glo4002.billing.memory.MemorySubmission;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class BillFactory {

	private long clientId;
	private String creationDate;
	private Cart items;
	private DueTerm dueTerm;
	private BigDecimal total;
	private ErrorStack errorStack;

	private IdBill indice = new IdBill();
	private MemorySubmission memBill = new MemorySubmission();

	@JsonCreator
	public BillFactory(@JsonProperty("clientId") long clientId, @JsonProperty("creationDate") String creationDate,
			@JsonProperty("dueTerm") DueTerm dueTerm, @JsonProperty("items") List<ItemForBill> items) {
		this.errorStack = new ErrorStack();
		setClientId(clientId);
		setDate(creationDate);
		setItems(items);
		setDueTerm(dueTerm);
		this.total = this.items.total(this.errorStack);
	}

	private void setClientId(long clientId) {
		this.clientId = clientId;
		MemoryClients.checkClientExists(this.clientId, this.errorStack);
	}

	private void setDate(String creationDate) {
		if (creationDate == null)
			this.creationDate = DateManager.defaultDate();
		else
			this.creationDate = creationDate;
	}

	private void setItems(List<ItemForBill> items) {
		this.items = new Cart();
		for (ItemForBill item : items)
			this.items.addItem(item, this.errorStack);
		this.items.checkAllItems(this.errorStack);
	}

	private void setDueTerm(DueTerm dueTerm) {
		if (dueTerm == null)
			this.dueTerm = DueTerm.IMMEDIATE;
		else
			this.dueTerm = dueTerm;
	}

	public BillDto proccessing() throws Exception {
		if (errorStack.empty()) {
			memBill.saveSubmissions(new Submission(this.clientId, this.creationDate, this.dueTerm, this.items));
			return new BillDto(this.indice.next(), total, this.dueTerm);
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
