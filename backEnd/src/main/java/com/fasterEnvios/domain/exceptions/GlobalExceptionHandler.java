package com.fasterEnvios.domain.exceptions;

import com.fasterEnvios.domain.exceptions.city.CityNotFoundException;
import com.fasterEnvios.domain.exceptions.client.ExternalServiceException;
import com.fasterEnvios.domain.exceptions.client.JsonMapperInternalServiceException;
import com.fasterEnvios.domain.exceptions.client.JsonMapperResponseClientException;
import com.fasterEnvios.domain.exceptions.jdbc.SaveErrorDataBaseException;
import com.fasterEnvios.domain.exceptions.payment.PaymentMethodNotFoundException;
import com.fasterEnvios.domain.exceptions.person.PersonNotFoundException;
import com.fasterEnvios.domain.exceptions.role.RoleNotFoundDataBaseException;
import com.fasterEnvios.domain.exceptions.shipment.ShipmentNotFoundException;
import com.fasterEnvios.domain.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({SaveErrorDataBaseException.class,  JsonMapperInternalServiceException.class  })
    public ResponseEntity<ErrorDetails> handleSaveErrorDataBaseException(RuntimeException ex) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler({UserNotFoundException.class,
            ShipmentNotFoundException.class,
            PaymentMethodNotFoundException.class,
            RoleNotFoundDataBaseException.class,
            PersonNotFoundException.class,
            CityNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleFindDataBaseException(RuntimeException ex) {
        return buildResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExternalServiceException.class, JsonMapperResponseClientException.class})
    public ResponseEntity<ErrorDetails> handleExternalServiceException(RuntimeException ex) {
        return buildResponse(ex, HttpStatus.BAD_GATEWAY);
    }


    public record ErrorDetails(int status, String message){
        public ErrorDetails(int status, String message){
            this.status = status;
            this.message = message;
        }
    }
    private ResponseEntity<ErrorDetails> buildResponse(Exception ex, HttpStatus status) {
        ErrorDetails error = new ErrorDetails(status.value(), ex.getMessage());
        return new ResponseEntity<>(error, status);
    }
}
