package ca.ulaval.glo4002.billing.itemsManager;

import ca.ulaval.glo4002.errorManager.ErrorNegativeItemPrice;
import ca.ulaval.glo4002.errorManager.ErrorNegativeTotal;
import ca.ulaval.glo4002.errorManager.ErrorStack;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Cart {

  private BigDecimal total;
  @JsonSerialize
  private ArrayList<ItemForSubmission> listItems;

  @JsonCreator
  public Cart() {
    total = new BigDecimal(0);
    listItems = new ArrayList<>();
  }

  public void addItem(ItemForSubmission itemForSubmission,
                      ErrorStack errorList) {
    listItems.add(itemForSubmission);
    if (itemForSubmission.price().doubleValue() < 0) {
      errorList.addError(new ErrorNegativeItemPrice(itemForSubmission.price().doubleValue()));
    }

    total = new BigDecimal(total.doubleValue() + itemForSubmission.total().doubleValue());
  }

  public void checkAllItems(ErrorStack errorList) {
    for (ItemForSubmission item : this.listItems) {
      item.check(errorList);
    }
  }

  public BigDecimal total(ErrorStack errorList) {
    if (this.total.doubleValue() < 0) {
      errorList.addError(new ErrorNegativeTotal(this.total.doubleValue()));
    }

    return this.total;
  }

}
