package ca.ulaval.glo4002.billing.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.billing.dto.ErrorsDto;
import ca.ulaval.glo4002.billing.dto.ExceptionDto;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

	@Override
	public Response toResponse(NotFoundException arg0) {
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setDescription(arg0.getDescription());
		exceptionDto.setEntity(arg0.getEntity());
		exceptionDto.setError(arg0.getError());

		ErrorsDto errors = new ErrorsDto();
		errors.addError(exceptionDto);
		return Response.status(Status.BAD_REQUEST).entity(errors).build();
	}

}
