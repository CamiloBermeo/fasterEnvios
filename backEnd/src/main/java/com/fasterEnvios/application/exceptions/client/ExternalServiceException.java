package com.fasterEnvios.application.exceptions.client;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String serviceName, int statusCode, String body) {
        super("Error en el servicio: "+serviceName+ " ha indicado el codigo de estado: "+ statusCode+ " junto con este body: "+ body);
    }
}
