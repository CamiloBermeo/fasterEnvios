package com.fasterEnvios.domain.exceptions.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("el usuario -"+email+"- no se encuentra registrado en la base de datos ");
    }
}
