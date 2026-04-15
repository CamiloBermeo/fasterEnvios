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
                                   CityDescription citySenderDB,
                                   CityDescription cityAddresseeDB) {

        return Shipment.builder()
                .withSender(Person.builder()
                        .withName(dto.sender().name())
                        .withLastName(dto.sender().lastName())
                        .withIdentityDocument(dto.sender().identityDocument())
                        .withPhoneNumber(dto.sender().phoneNumber())
                        .withAddress(dto.sender().address())
                        .withCity(citySenderDB)
                        .build()

                )
                .withAddressee(Person.builder()
                        .withName(dto.addressee().name())
                        .withLastName(dto.addressee().lastName())
                        .withIdentityDocument(dto.addressee().identityDocument())
                        .withPhoneNumber(dto.addressee().phoneNumber())
                        .withAddress(dto.addressee().address())
                        .withCity(cityAddresseeDB)
                        .build()
                )
                .withState(state)
                .withDistance(distance)
                .withEstimatedDeliveryDate(estimatedDeliveryDate)
                .withTotalAmount(totalAmount)
                .withCreatedAt(LocalDateTime.now())
                .withPackages(
                        PackageModel.builder()
                                .withDeclaredValue(dto.packages().declaredValue())
                                .withDescription(dto.packages().description())
                                .withDimensions(dto.packages().dimensions())
                                .withWeightKg(dto.packages().weightKg())
                                .build())

                .withPaymentTransaction(
                        PaymentTransaction.builder()
                                .withAmount(dto.paymentTransaction().amount())
                                .withPaymentStatus(PaymentStatusEnum.valueOf(dto.paymentTransaction().paymentStatus().toString()))
                                .withPaymentDate(LocalDateTime.now())
                                .withPaymentMethods(PaymentMethod.builder()
                                        .withMethodName(dto.paymentTransaction().methodPaymentName())
                                        .build()
                                )
                                .build()
                ).build();
    }

    public static NewShipmentResponseDTO toDto(Shipment shipment){
        return new NewShipmentResponseDTO(
                shipment.getId(),
                shipment.getState().toString(),
                shipment.getSender().getCity().getName(),
                shipment.getAddressee().getCity().getName(),
                String.valueOf(shipment.getDistance()+"Km"),
                String.valueOf("$"+shipment.getTotalAmount()+" COP"),
                shipment.getEstimatedDeliveryDate(),
                shipment.getCreatedAt()
        );
    }

}
