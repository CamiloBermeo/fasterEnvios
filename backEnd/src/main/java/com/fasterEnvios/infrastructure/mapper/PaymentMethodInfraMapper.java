package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;

import java.util.List;

public class PaymentMethodInfraMapper {

    public PaymentMethodEntity toEntity(PaymentMethod paymentMethod) {
        return PaymentMethodEntity.builder()
                .methodName(paymentMethod.getMethodName())
                .status(paymentMethod.isStatus())
                .build();
    }

    public List<PaymentMethodEntity> listToEntity(List<PaymentMethod> paymentMethods) {
        if (paymentMethods == null) return List.of();
        return  paymentMethods.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<PaymentMethod> toModelList(List<PaymentMethodEntity> entities) {
        return entities.stream().map( entity -> PaymentMethod.builder()
                .withId(entity.getId())
                .withMethodName(entity.getMethodName())
                .withStatus(entity.isStatus())
                .build()
        ).toList();

    }
}
