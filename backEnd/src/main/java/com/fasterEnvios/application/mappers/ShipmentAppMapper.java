package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.model.PackageModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShipmentAppMapper {

    public static Shipment toModel(NewShipmentRequestDTO dto, LocalDateTime estimatedDeliveryDate, double distance, StateEnum state, BigDecimal totalAmount) {

        return Shipment.builder()
                .withCityOrigin(dto.cityOrigin())
                .withCityDestination(dto.cityDestination())
                .withState(state)
                .withDistance(distance)
                .withEstimatedDeliveryDate(estimatedDeliveryDate)
                .withTotalAmount(totalAmount)
                .withPackages(
                        List.of(PackageModel.builder()
                                .withDeclaredValue(dto.packages().declaredValue())
                                .withDescription(dto.packages().description())
                                .withDimensions(dto.packages().dimensions())
                                .withWeightKg(dto.packages().weightKg())
                                .build()))
                .withPaymentTransaction(
                        PaymentTransaction.builder()
                                .withAmount(dto.paymentTransaction().amount())
                                .withPaymentStatus(PaymentStatusEnum.valueOf(dto.paymentTransaction().paymentStatus().toString()))
                                .withPaymentDate(LocalDateTime.now())
                                .withPaymentMethods(List.of())
                                .build()
                ).build();
    }

    public static NewShipmentResponseDTO toDto(Shipment shipment){
        return new NewShipmentResponseDTO(
                shipment.getId(),
                shipment.getState().toString(),
                shipment.getCityOrigin(),
                shipment.getCityDestination(),
                shipment.getDistance(),
                shipment.getTotalAmount(),
                shipment.getEstimatedDeliveryDate(),
                shipment.getCreatedAt(),
                shipment.getPackageModels().size()
        );
    }

}
