package com.fasterEnvios.application.dto.shipment;

import com.fasterEnvios.domain.model.PaymentStatusEnum;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;

import java.time.LocalDateTime;
import java.util.List;

public record PaymentTransactionRequestDTO(
        PaymentStatusEnum paymentMethods,
        float amount
) {
}
