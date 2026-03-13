package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.model.PackageModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShipmentAppMapper {

    public static Shipment toModel(NewShipmentRequestDTO dto,
                                   LocalDateTime estimatedDeliveryDate,
                                   double distance,
                                   StateEnum state,
                                   BigDecimal totalAmount,
                                   CityDescription cityOriginDB,
                                   CityDescription cityDestinationDB) {

        return Shipment.builder()
                .withCityOrigin(cityOriginDB)
                .withCityDestination(cityDestinationDB)
                .withState(state)
                .withDistance(distance)
                .withEstimatedDeliveryDate(estimatedDeliveryDate)
                .withTotalAmount(totalAmount)
                .withCreatedAt(LocalDateTime.now())
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
                shipment.getCityOrigin().getName(),
                shipment.getCityDestination().getName(),
                shipment.getDistance(),
                shipment.getTotalAmount(),
                shipment.getEstimatedDeliveryDate(),
                shipment.getCreatedAt(),
                shipment.getPackageModels().size()
        );
    }

}
