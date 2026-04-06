package com.fasterEnvios.application.exceptions;

import com.fasterEnvios.application.exceptions.client.ExternalServiceException;
import com.fasterEnvios.application.exceptions.client.JsonMapperInternalServiceException;
import com.fasterEnvios.application.exceptions.client.JsonMapperResponseClientException;
import com.fasterEnvios.application.exceptions.jdbc.SaveErrorDataBaseException;
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

    @ExceptionHandler({ExternalServiceException.class, JsonMapperResponseClientException.class})
    public ResponseEntity<ErrorDetails> handleExternalServiceException(RuntimeException ex) {
        return buildResponse(ex, HttpStatus.BAD_GATEWAY);
    }


    private record ErrorDetails(int status, String message){
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
