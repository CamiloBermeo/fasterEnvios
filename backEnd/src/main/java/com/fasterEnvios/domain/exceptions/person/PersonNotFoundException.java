package com.fasterEnvios.domain.exceptions.person;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String identifier) {
        super("La persona: "+identifier+" no existe en base de datos");
    }
}
