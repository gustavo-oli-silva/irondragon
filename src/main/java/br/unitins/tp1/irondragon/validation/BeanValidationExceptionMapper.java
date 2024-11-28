package br.unitins.tp1.irondragon.validation;

import br.unitins.tp1.irondragon.resource.LoteResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
@ApplicationScoped
public class BeanValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private static final Logger LOGGER = Logger.getLogger(BeanValidationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        LOGGER.error("Bean Validation Error " + exception.getMessage());
        ValidationError validationError = new ValidationError(400, "Erro de validação");

        for(ConstraintViolation<?> violation: exception.getConstraintViolations()) {
            int index = violation.getPropertyPath().toString().lastIndexOf('.');
            String fieldName = violation.getPropertyPath().toString().substring(index+1);

            validationError.addFieldError(fieldName, violation.getMessage());
        }

        return Response.status(400).entity(validationError).build();
    }
}
