package com.fasterEnvios.application.exceptions.client;

public class JsonMapperResponseClientException extends RuntimeException {
    public JsonMapperResponseClientException(String nameApi) {
        super("La el json de la respuesta del cliente: "+nameApi+" no coincide con el mapper establecido");
    }
}
