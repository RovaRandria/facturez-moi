package errorManager;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ErrorStack {

	@JsonSerialize
	private List<ErrorService> errors;

	@JsonCreator
	public ErrorStack() {
		this.errors = new ArrayList<ErrorService>();
	}

	public void addError(ErrorService error) {
		this.errors.add(error);
	}

	public boolean isEmpty() {
		return errors.isEmpty();
	}

}
