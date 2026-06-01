package com.fasterEnvios.domain.exceptions.city;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String cityName) {
        super("la Ciudad: " + cityName + " no esta registrada");
    }
}
