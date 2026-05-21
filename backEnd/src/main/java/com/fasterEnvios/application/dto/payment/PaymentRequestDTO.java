package com.fasterEnvios.application.dto.payment;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        String orderId,
        String payingPerson,
        String methodPaymentName,
        String observation
) {
}
