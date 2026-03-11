package com.fasterEnvios.infrastructure.entity;

import com.fasterEnvios.domain.model.StateEnum;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ShipmentEntity {

    private  Long id;
    private PaymentTransactionEntity  paymentTransaction;
    private List<PackageEntity>  packages;
    private LocalDateTime  createdAt;
    private LocalDateTime estimatedDeliveryDate;
    private String cityOrigin;
    private String cityDestination;
    private double distance;
    private String state;

}
