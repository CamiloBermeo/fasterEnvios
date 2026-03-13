package com.fasterEnvios.infrastructure.entity;

import com.fasterEnvios.domain.model.StateEnum;
import lombok.*;

import java.math.BigDecimal;
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
    private CityDescriptionEntity cityOrigin;
    private CityDescriptionEntity cityDestination;
    private BigDecimal totalAmount;
    private double distance;
    private String state;

}
