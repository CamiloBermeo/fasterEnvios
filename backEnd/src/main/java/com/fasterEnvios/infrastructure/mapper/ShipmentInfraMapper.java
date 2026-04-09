package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.infrastructure.entity.PackageEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import com.fasterEnvios.infrastructure.entity.PersonEntity;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipmentInfraMapper {

    public static ShipmentEntity toEntity(Shipment shipment) {
        return ShipmentEntity.builder()
                .sender(PersonInfraMapper.toEntity(shipment.getSender()))
                .addressee(PersonInfraMapper.toEntity(shipment.getAddressee()))
                .distance(shipment.getDistance())
                .createdAt(shipment.getCreatedAt())
                .estimatedDeliveryDate(shipment.getEstimatedDeliveryDate())
                .state(shipment.getState().toString())
                .totalAmount(shipment.getTotalAmount())
                .packages(PackageInfraMapper.toEntity(shipment.getPackageModels()))
                .paymentTransaction(PaymentTransactionInfraMapper.toEntity(shipment.getPaymentTransaction()))
                .build();

    }

    public static Shipment toModel(ShipmentEntity shipmentEntity) {
        Shipment.ShipmentBuilder shipmentBuilder = Shipment.builder()
                .withId(shipmentEntity.getId())
                .withSender(PersonInfraMapper.toModel(shipmentEntity.getSender()))
                .withAddressee(PersonInfraMapper.toModel(shipmentEntity.getAddressee()))
                .withDistance(shipmentEntity.getDistance())
                .withCreatedAt(shipmentEntity.getCreatedAt())
                .withEstimatedDeliveryDate(shipmentEntity.getEstimatedDeliveryDate())
                .withState(StateEnum.valueOf(shipmentEntity.getState()))
                .withTotalAmount(shipmentEntity.getTotalAmount())
                .withPackages(PackageInfraMapper.toModel(shipmentEntity.getPackages()))
                .withPaymentTransaction(PaymentTransactionInfraMapper.toModel(shipmentEntity.getPaymentTransaction()))
                .build().toBuilder();
        return shipmentBuilder.build();
    }

}
