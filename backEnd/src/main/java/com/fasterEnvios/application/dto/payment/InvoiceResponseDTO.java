package com.fasterEnvios.application.dto.payment;

import com.fasterEnvios.domain.model.PaymentStatusEnum;

import java.time.LocalDateTime;

public record InvoiceResponseDTO(
        String trackingNumber,
        String senderName,
        String senderAddress,
        String senderCity,
        String addresseeName,
        String addresseeAddress,
        String addresseeCity,
        String packageDescription,
        double packageWeight,
        LocalDateTime estimateDeliveryDate,
        String idTransaction,
        String paymentMethod,
        LocalDateTime paymentDate,
        String paymentStatus,
        String InvoiceIssueDate
) {
}
