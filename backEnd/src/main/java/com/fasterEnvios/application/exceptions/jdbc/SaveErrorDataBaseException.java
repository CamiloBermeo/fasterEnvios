package com.fasterEnvios.application.exceptions.jdbc;

public class SaveErrorDataBaseException extends RuntimeException {
    public SaveErrorDataBaseException(String city) {
        super("Error al guardar en base de datos la ciudad: " + city);
    }
}
