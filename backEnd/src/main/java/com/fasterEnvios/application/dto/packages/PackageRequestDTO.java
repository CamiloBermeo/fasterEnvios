package com.fasterEnvios.application.dto.packages;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PackageRequestDTO (
        @NotNull(message = "debes colocar el peso de este paquete")
        @Negative(message = "el peso no puede ser negativo")
        double weightKg,
        @NotNull(message = "debes colocar las dimensiones de este paquete")
        @Negative(message = "las dimensiones no pueden ser negativas")
        double dimensions,
        @NotNull(message = "debes colocar el valor declarado de este paquete")
        @Negative(message = "el valor no pueden ser negativo")
        BigDecimal declaredValue,
        @NotBlank(message = "debes poner una descripcion valida")
        String description
){
}
