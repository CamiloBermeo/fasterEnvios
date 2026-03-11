package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.PackageModel;
import com.fasterEnvios.domain.model.PaymentTransaction;
import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.model.StateEnum;
import com.fasterEnvios.infrastructure.entity.PackageEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;

import java.util.List;

public class ShipmentInfraMapper {

    public static ShipmentEntity toEntity(Shipment shipment, List<PackageEntity> packageEntity, PaymentTransactionEntity paymentTransaction) {
        return ShipmentEntity.builder()
                .cityDestination(shipment.getCityDestination())
                .cityOrigin(shipment.getCityOrigin())
                .distance(shipment.getDistance())
                .createdAt(shipment.getCreatedAt())
                .estimatedDeliveryDate(shipment.getEstimatedDeliveryDate())
                .state(shipment.getState().toString())
                .packages(packageEntity)
                .paymentTransaction(paymentTransaction)
                .build();

    }

    public static Shipment toModel(ShipmentEntity shipmentEntity, Long shipmentId,List<PackageModel> packageModel, PaymentTransaction paymentTransaction) {
        Shipment.ShipmentBuilder shipmentBuilder = Shipment.builder()
                .withId(shipmentId)
                .withCityDestination(shipmentEntity.getCityDestination())
                .withCityOrigin(shipmentEntity.getCityOrigin())
                .withDistance(shipmentEntity.getDistance())
                .withCreatedAt(shipmentEntity.getCreatedAt())
                .withEstimatedDeliveryDate(shipmentEntity.getEstimatedDeliveryDate())
                .withState(StateEnum.valueOf(shipmentEntity.getState()))
                .withPackages(packageModel)
                .withPaymentTransaction(paymentTransaction)
                .build().toBuilder();
        return shipmentBuilder.build();
    }

}
