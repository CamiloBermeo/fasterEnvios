package com.fasterEnvios.application.dto.shipment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NewShipmentResponseDTO (
        Long trackingNumber,       // ID del envío (Guía)
        String status,               // Estado actual
        String originCity,           // Ciudad de origen
        String destinationCity,      // Ciudad de destino
        double distance,
        BigDecimal totalAmount,      // Costo total del envío
        LocalDateTime estimatedDate, // Fecha estimada de llegada
        LocalDateTime createdAt,     // Fecha de creación
        int packageCount
){
}
