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
                .shipment(ShipmentInfraMapper.toEntity(paymentTransaction.getShipments()))
                .payingPerson(PersonInfraMapper.toEntity(paymentTransaction.getPayingPerson()))
                .paymentDate(paymentTransaction.getPaymentDate())
                .amount(paymentTransaction.getAmount())
                .paymentMethod(PaymentMethodInfraMapper.toEntity(paymentTransaction.getPaymentMethods()))
                .paymentStatus(paymentTransaction.getPaymentStatus().toString())
                .observations(paymentTransaction.getObservations())
                .build();
    }

    public static PaymentTransaction toModel(PaymentTransactionEntity entity) {
        PaymentTransaction.PaymentTransactionBuilder paymentTransactionBuilder = PaymentTransaction.builder()
                .withId(entity.getId())
                .withIdTransaction(entity.getIdTransaction())
                .withShipments(ShipmentInfraMapper.toModel(entity.getShipment()))
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
