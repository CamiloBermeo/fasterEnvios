package com.fasterEnvios.application.dto.payment;

import com.fasterEnvios.domain.model.PaymentStatusEnum;

import java.math.BigDecimal;

public record PaymentTransactionRequestDTO(
        PaymentStatusEnum paymentStatus,
        BigDecimal amount,
        String methodPaymentName
) {
}
