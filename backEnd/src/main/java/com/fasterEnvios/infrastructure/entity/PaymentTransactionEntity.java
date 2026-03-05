package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionEntity {
    private Long id;
    private List<ShipmentEntity> shipments;
    private List<PaymentMethodEntity> paymentMethods;
    private float amount;
    private LocalDateTime paymentDate;
    private PaymentStatusEnum paymentStatus;
}
