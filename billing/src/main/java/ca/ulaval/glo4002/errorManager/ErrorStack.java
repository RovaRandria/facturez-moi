package ca.ulaval.glo4002.errorManager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

public class ErrorStack {

  @JsonSerialize
  private List<ErrorBilling> errors;

  @JsonCreator
  public ErrorStack() {
    this.errors = new ArrayList<ErrorBilling>();
  }

  public void addError(ErrorBilling error) {
    this.errors.add(error);
  }

  public boolean empty() {
    return errors.isEmpty();
  }

  public boolean containsError(ErrorBilling errorBilling) {
    for (ErrorBilling error : errors)
      if (error.equals(errorBilling))
        return true;
    return false;
  }

}
