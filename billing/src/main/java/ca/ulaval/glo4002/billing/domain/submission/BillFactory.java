package ca.ulaval.glo4002.billing.domain.submission;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.application.ClientService;
import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.itemsManager.Cart;
import ca.ulaval.glo4002.billing.itemsManager.ItemForBill;
import ca.ulaval.glo4002.billing.memory.MemoryBill;
import ca.ulaval.glo4002.errorManager.ErrorStack;

public class BillFactory {

	@JsonSerialize
	private long clientId;
	@JsonSerialize
	private String creationDate;
	@JsonSerialize
	private Cart items;
	@JsonSerialize
	private DueTerm dueTerm;

	private BigDecimal total;
	private ErrorStack errorList;

	private IdBill indice = new IdBill();
	private MemoryBill memBill = new MemoryBill();

	@JsonCreator
	public BillFactory(@JsonProperty("clientId") long clientId, @JsonProperty("creationDate") String creationDate,
			@JsonProperty("dueTerm") DueTerm dueTerm, @JsonProperty("items") List<ItemForBill> items) {
		this.errorList = new ErrorStack();
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.items = new Cart();
		for (ItemForBill item : items)
			this.items.addItem(item, this.errorList);
		this.dueTerm = dueTerm;

		factoryInspector();
	}

	private void factoryInspector() {
		this.total = this.items.total(this.errorList);
		(new ClientService()).checkClientExists(this.clientId, this.errorList);
		this.items.checkAllItems(this.errorList);
	}

	public void sendError(ca.ulaval.glo4002.errorManager.ErrorService error) {
		if (errorList == null)
			this.errorList = new ErrorStack();
		this.errorList.addError(error);
	}

	public Object wayOutFactory() {
		if (errorList.empty()) {
			memBill.saveBill(new Submission(this.clientId, this.creationDate, this.dueTerm, this.items));
			return new BillDto(this.indice, total, this.dueTerm);
		} else {
			return this.errorList;
		}
	}

}
