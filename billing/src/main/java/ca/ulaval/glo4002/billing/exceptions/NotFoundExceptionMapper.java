package ca.ulaval.glo4002.billing.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.dto.ErrorsDto;
import ca.ulaval.glo4002.billing.dto.ExceptionDto;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<BillingException> {

	@Override
	public Response toResponse(BillingException arg0) {
		ExceptionDto exceptionDto = new ExceptionDto(arg0.getError(), arg0.getDescription(), arg0.getEntity());

		ErrorsDto errors = new ErrorsDto();
		errors.addError(exceptionDto);
		return Response.status(Status.BAD_REQUEST).entity(errors).build();
	}

}
