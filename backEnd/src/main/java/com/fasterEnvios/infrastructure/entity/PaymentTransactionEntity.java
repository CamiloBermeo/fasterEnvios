package com.fasterEnvios.infrastructure.entity;

import com.fasterEnvios.domain.model.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTransactionEntity {
    private Long id;
    private List<ShipmentEntity> shipments;
    private List<PaymentMethodEntity> paymentMethods;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String paymentStatus;
}
