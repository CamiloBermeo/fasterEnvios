package com.fasterEnvios.application.dto.packages;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PackageRequestDTO (
        @NotBlank(message = "debes colocar el peso de este paquete")
        @Negative(message = "el peso no puede ser negativo")
        double weightKg,
        @NotBlank(message = "debes colocar las dimensiones de este paquete")
        @Negative(message = "las dimensiones no pueden ser negativas")
        double dimensions,
        @NotBlank(message = "debes colocar el valor declarado de este paquete")
        @Negative(message = "el valor no pueden ser negativo")
        BigDecimal declaredValue,
        @NotBlank(message = "debes poner una descripcion valida")
        String description
){
}
