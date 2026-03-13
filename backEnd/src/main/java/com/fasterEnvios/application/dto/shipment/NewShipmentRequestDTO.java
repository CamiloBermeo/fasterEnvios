package com.fasterEnvios.application.dto.shipment;

import com.fasterEnvios.application.dto.packages.PackageRequestDTO;
import com.fasterEnvios.domain.model.StateEnum;
import jakarta.validation.constraints.NotBlank;

public record NewShipmentRequestDTO(
        @NotBlank(message = "debes colocar una ciudad de origen")
        String cityOrigin,
        @NotBlank(message = "debes colocar una ciudad de destino")
        String cityDestination,
        PackageRequestDTO packages,
        StateEnum status,
        PaymentTransactionRequestDTO paymentTransaction
) {
}
