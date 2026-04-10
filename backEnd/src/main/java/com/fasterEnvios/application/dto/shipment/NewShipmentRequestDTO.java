package com.fasterEnvios.application.dto.shipment;

import com.fasterEnvios.application.dto.packages.PackageRequestDTO;
import com.fasterEnvios.application.dto.person.PersonRequestDTO;
import com.fasterEnvios.domain.model.StateEnum;

public record NewShipmentRequestDTO(
        PersonRequestDTO sender,//remitente
        PersonRequestDTO addressee,//destinatario
        PackageRequestDTO packages,
        StateEnum status,
        PaymentTransactionRequestDTO paymentTransaction
) {
}
