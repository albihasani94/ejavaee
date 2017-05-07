package al.ikubinfo.doit.business;

import javax.ejb.EJBException;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {
	
	@Override
	public Response toResponse(EJBException ex) {
		Throwable cause = ex.getCause();
		Response unknownError = Response.serverError().header("cause", ex.toString()).build();
		if (cause == null) {
			return unknownError;
		}
		 
		if (cause instanceof OptimisticLockException) {
			return Response.status(Response.Status.CONFLICT).
				   header("cause", "Conflict occurred: " + cause).
				   build();
		}
		
		return unknownError;
	}

}
