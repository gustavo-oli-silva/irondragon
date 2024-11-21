package br.unitins.tp1.irondragon.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class BeanValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ValidationError validationError = new ValidationError(400, "Erro de validação");

        for(ConstraintViolation<?> violation: exception.getConstraintViolations()) {
            int index = violation.getPropertyPath().toString().lastIndexOf('.');
            String fieldName = violation.getPropertyPath().toString().substring(index+1);

            validationError.addFieldError(fieldName, violation.getMessage());
        }

        return Response.status(400).entity(validationError).build();
    }
}
