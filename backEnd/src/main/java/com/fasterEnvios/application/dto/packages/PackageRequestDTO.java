package com.fasterEnvios.application.dto.packages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PackageRequestDTO (
        @NotNull(message = "debes colocar el peso de este paquete")
        @Positive(message = "el peso no puede ser negativo")
        Double weightKg,
        @NotNull(message = "debes colocar las dimensiones de este paquete")
        @Positive(message = "las dimensiones no pueden ser negativas")
        Double dimensions,
        @NotNull(message = "debes colocar el valor declarado de este paquete")
        @Positive(message = "el valor no pueden ser negativo")
        BigDecimal declaredValue,
        @NotBlank(message = "debes poner una descripcion valida")
        String description
){
}
