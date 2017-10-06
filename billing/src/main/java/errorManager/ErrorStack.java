package errorManager;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ErrorStack {

	@JsonSerialize
	private List<Error> errors;

	@JsonCreator
	public ErrorStack() {
		this.errors = new ArrayList<Error>();
	}

	public void addError(Error error) {
		this.errors.add(error);
	}

	public boolean isEmpty() {
		return errors.isEmpty();
	}

}
