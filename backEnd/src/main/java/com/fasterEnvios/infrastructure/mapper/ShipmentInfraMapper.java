package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.PackageModel;
import com.fasterEnvios.domain.model.PaymentTransaction;
import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.model.StateEnum;
import com.fasterEnvios.infrastructure.entity.PackageEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipmentInfraMapper {

    public static ShipmentEntity toEntity(Shipment shipment, PackageEntity packageEntity, PaymentTransactionEntity paymentTransaction) {
        return ShipmentEntity.builder()
                .cityDestination(CityInfraMapper.toEntity(shipment.getCityDestination()))
                .cityOrigin(CityInfraMapper.toEntity(shipment.getCityOrigin()))
                .distance(shipment.getDistance())
                .createdAt(shipment.getCreatedAt())
                .estimatedDeliveryDate(shipment.getEstimatedDeliveryDate())
                .state(shipment.getState().toString())
                .totalAmount(shipment.getTotalAmount())
                .packages(packageEntity)
                .paymentTransaction(paymentTransaction)
                .build();

    }

    public static Shipment toModel(ShipmentEntity shipmentEntity,PackageModel packageModel, PaymentTransaction paymentTransaction) {
        Shipment.ShipmentBuilder shipmentBuilder = Shipment.builder()
                .withId(shipmentId)
                .withCityDestination(CityInfraMapper.toDomain(shipmentEntity.getCityDestination()))
                .withCityOrigin(CityInfraMapper.toDomain(shipmentEntity.getCityOrigin()))
                .withDistance(shipmentEntity.getDistance())
                .withCreatedAt(shipmentEntity.getCreatedAt())
                .withEstimatedDeliveryDate(shipmentEntity.getEstimatedDeliveryDate())
                .withState(StateEnum.valueOf(shipmentEntity.getState()))
                .withTotalAmount(shipmentEntity.getTotalAmount())
                .withPackages(packageModel)
                .withPaymentTransaction(paymentTransaction)
                .build().toBuilder();
        return shipmentBuilder.build();
    }

}
