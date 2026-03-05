package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentEntity {

    private  Long id;
    private PaymentTransactionEntity  paymentTransaction;
    private List<PackageEntity>  packages;
    private LocalDateTime  createdAt;
    private LocalDateTime estimatedDeliveryDate;
    private String cityOrigin;
    private String cityDestination;
    private StateEnum state;

}
