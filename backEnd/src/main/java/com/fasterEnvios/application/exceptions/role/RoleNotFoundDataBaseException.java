package com.fasterEnvios.application.exceptions.role;

public class RoleNotFoundDataBaseException extends RuntimeException {
    public RoleNotFoundDataBaseException(String role) {
        super("role " + role + " no encontrado en la base de datos");
    }
}
