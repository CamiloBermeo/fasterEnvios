package com.fasterEnvios.domain.exceptions.user;

public class ExistingUserDatabaseException extends RuntimeException {
    public ExistingUserDatabaseException(String document) {
        super("El usuario con documento "+document+" ya esta registrado");
    }
}
