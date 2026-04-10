package com.fasterEnvios.application.dto.person;

import com.fasterEnvios.application.dto.city.CityRequestDTO;
import jakarta.validation.constraints.NotBlank;

public record PersonRequestDTO(
        @NotBlank(message = "debes colocar un nombre válido ")
        String name,
        @NotBlank(message = "debes colocar un apellido válido ")
        String lastName,
        @NotBlank(message = "debes colocar un numero de documento válido ")
        String identityDocument,
        @NotBlank(message = "debes colocar un numero de telefono válido ")
        String phoneNumber,
        @NotBlank(message = "debes colocar una direccion válida ")
        String address,
        CityRequestDTO city

) {
}
