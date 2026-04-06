package com.fasterEnvios.application.exceptions.client;

public class JsonMapperInternalServiceException extends RuntimeException {
    public JsonMapperInternalServiceException(String nameApi) {
        super("Error en convertir el json del servidor para hacer consulta con el cliente: "+ nameApi);
    }
}
