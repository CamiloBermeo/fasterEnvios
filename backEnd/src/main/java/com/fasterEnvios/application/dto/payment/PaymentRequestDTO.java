package com.fasterEnvios.application.dto.payment;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        Long orderId,
        String payingPerson,
        BigDecimal amount,
        String methodPaymentName,
        String observation
) {
}
