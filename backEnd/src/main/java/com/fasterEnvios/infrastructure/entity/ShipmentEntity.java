package com.fasterEnvios.infrastructure.entity;

import com.fasterEnvios.domain.model.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentEntity {

    private  Long id;
    private PaymentTransactionEntity  paymentTransaction;
    private List<PackageEntity>  packages;
    private LocalDateTime  createdAt;
    private LocalDateTime estimatedDeliveryDate;
    private String cityOrigin;
    private String cityDestination;
    private String state;

}
