package com.fasterEnvios.application.dto.city;

import jakarta.validation.constraints.NotNull;

public record CityRequestDTO(
        @NotNull(message = "Debes colocar el nombre de la ciudad")
        String name
) {
}
