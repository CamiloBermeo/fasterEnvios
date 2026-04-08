package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodInfraMapper {

    public static PaymentMethodEntity toEntity(PaymentMethod paymentMethod) {
        return PaymentMethodEntity.builder()
                .methodName(paymentMethod.getMethodName())
                .status(paymentMethod.isStatus())
                .build();
    }

    public List<PaymentMethod> toModelList(List<PaymentMethodEntity> entities) {
        return entities.stream().map(entity -> PaymentMethod.builder()
                .withId(entity.getId())
                .withMethodName(entity.getMethodName())
                .withStatus(entity.isStatus())
                .build()
        ).toList();

    }
    /*con este metodo puedo mappear listas
    public List<PaymentMethodEntity> listToEntity(List<PaymentMethod> paymentMethods) {
        if (paymentMethods == null) return List.of();
        return paymentMethods.stream()
                .map(this::toEntity)
                .toList();
    }
*/
}
