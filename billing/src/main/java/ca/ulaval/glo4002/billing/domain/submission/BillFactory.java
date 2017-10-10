package ca.ulaval.glo4002.billing.domain.submission;

import ca.ulaval.glo4002.billing.application.ClientService;
import ca.ulaval.glo4002.billing.application.ProductService;
import ca.ulaval.glo4002.billing.domain.DateManager;
import ca.ulaval.glo4002.billing.domain.IdBill;
import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.dto.BillDto;
import ca.ulaval.glo4002.billing.itemsManager.Cart;
import ca.ulaval.glo4002.billing.itemsManager.ItemForSubmission;
import ca.ulaval.glo4002.billing.memory.MemoryClients;
import ca.ulaval.glo4002.billing.memory.MemoryProduct;
import ca.ulaval.glo4002.billing.memory.MemorySubmission;
import ca.ulaval.glo4002.errorManager.ErrorClientNotFound;
import ca.ulaval.glo4002.errorManager.ErrorStack;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class BillFactory {

  private long clientId;
  private String creationDate;
  private Cart items;
  private DueTerm dueTerm;
  private BigDecimal total;
  private ErrorStack errorStack;

	private IdBill indice;
	private MemorySubmission memBill;
	private MemoryClients memoryClients;
	private MemoryProduct memoryProduct;

	@JsonCreator
	public BillFactory(@JsonProperty("clientId") long clientId, @JsonProperty("creationDate") String creationDate,
			@JsonProperty("dueTerm") DueTerm dueTerm, @JsonProperty("items") List<ItemForSubmission> items) {
		this.errorStack = new ErrorStack();
		setClientId(clientId);
		setDate(creationDate);
		setItems(items);
		setDueTerm(dueTerm);
		this.total = this.items.total(this.errorStack);

		indice = new IdBill();
		memBill = new MemorySubmission();
		memoryClients = new MemoryClients();
		memoryProduct = new MemoryProduct();
		new ClientService(memoryClients);
		new ProductService(memoryProduct);
	}

	private void setClientId(long clientId) {
		this.clientId = clientId;
		if (!memoryClients.checkClientID(clientId)) {
			errorStack.addError(new ErrorClientNotFound(clientId));
		}
	}

  private void setDate(String creationDate) {
    if (creationDate == null) {
      this.creationDate = DateManager.defaultDate();
    } else {
      this.creationDate = creationDate;
    }
  }

  private void setItems(List<ItemForSubmission> items) {
    this.items = new Cart();
    for (ItemForSubmission item : items) {
      this.items.addItem(item, this.errorStack);
    }
    this.items.checkAllItems(this.errorStack);
  }

  private void setDueTerm(DueTerm dueTerm) {
    if (dueTerm == null) {
      this.dueTerm = DueTerm.IMMEDIATE;
    } else {
      this.dueTerm = dueTerm;
    }
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
