package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.application.mappers.ShipmentAppMapper;
import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.model.PaymentStatusEnum;
import com.fasterEnvios.domain.model.PaymentTransaction;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PaymentTransactionInfraMapper {

    public static PaymentTransactionEntity toEntity(PaymentTransaction paymentTransaction) {
        return PaymentTransactionEntity.builder()
                .idTransaction(paymentTransaction.getIdTransaction())
                .paymentDate(paymentTransaction.getPaymentDate())
                .amount(paymentTransaction.getAmount())
                .paymentStatus(paymentTransaction.getPaymentStatus().toString())
                .observations(paymentTransaction.getObservations())
                .build();
    }

    public static PaymentTransaction toModel(PaymentTransactionEntity entity) {
        if (entity == null) return null;
        PaymentTransaction.PaymentTransactionBuilder paymentTransactionBuilder = PaymentTransaction.builder()
                .withId(entity.getId())
                .withShipments(ShipmentInfraMapper.toModel(entity.getShipment()))
                .withIdTransaction(entity.getIdTransaction())
                .withPayingPerson(PersonInfraMapper.toModel(entity.getPayingPerson()))
                .withPaymentDate(entity.getPaymentDate())
                .withAmount(entity.getAmount())
                .withPaymentMethods(PaymentMethodInfraMapper.toModel(entity.getPaymentMethod()))
                .withPaymentStatus(PaymentStatusEnum.valueOf(entity.getPaymentStatus()))
                .withObservation(entity.getObservations())
                .build().toBuilder();
        return paymentTransactionBuilder.build();
    }
}
