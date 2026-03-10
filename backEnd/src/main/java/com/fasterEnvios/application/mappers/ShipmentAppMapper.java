package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.model.Package;
import com.fasterEnvios.infrastructure.entity.PackageEntity;

import java.time.LocalDateTime;
import java.util.List;

public class ShipmentAppMapper {

    public static Shipment toModel(NewShipmentRequestDTO dto, LocalDateTime estimatedDeliveryDate, double distance, StateEnum state) {
/*
*   private PaymentTransactionEntity  paymentTransaction;
    private String state;
* */
        return Shipment.builder()
                .withCityOrigin(dto.cityOrigin())
                .withCityDestination(dto.cityDestination())
                .withState(state)
                .withDistance(distance)
                .withEstimatedDeliveryDate(estimatedDeliveryDate)
                .withPackages(
                        List.of(Package.builder()
                                .withDeclaredValue(dto.packages().declaredValue())
                                .withDescription(dto.packages().description())
                                .withDimensions(dto.packages().dimensions())
                                .withWeightKg(dto.packages().weightKg())
                                .build()))
                .withPaymentTransaction(
                        PaymentTransaction.builder()
                                .withAmount(dto.paymentTransaction().amount())
                                .withPaymentStatus()
                                .withPaymentDate(LocalDateTime.now())
                                .withPaymentMethods(List.of(dto.paymentTransaction().paymentMethods()))
                                .build()
                );


    }

}
