package com.fasterEnvios.application.dto.payment;

import com.fasterEnvios.domain.model.PaymentStatusEnum;

public record PaymentResponseDTO(
        PaymentStatusEnum paymentStatus
) {
}
