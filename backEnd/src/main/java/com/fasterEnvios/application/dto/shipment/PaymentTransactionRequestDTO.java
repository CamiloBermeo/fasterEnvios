package com.fasterEnvios.application.dto.shipment;

import com.fasterEnvios.domain.model.PaymentStatusEnum;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PaymentTransactionRequestDTO(
        PaymentStatusEnum paymentStatus,
        BigDecimal amount,
        String methodPaymentName
) {
}
