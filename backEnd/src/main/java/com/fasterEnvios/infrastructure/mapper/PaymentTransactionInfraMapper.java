package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.model.PaymentStatusEnum;
import com.fasterEnvios.domain.model.PaymentTransaction;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;

import java.util.List;

public class PaymentTransactionInfraMapper {

    public PaymentTransactionEntity toEntity(PaymentTransaction paymentTransaction, List<PaymentMethodEntity> paymentMethods) {
        return PaymentTransactionEntity.builder()
                .paymentDate(paymentTransaction.getPaymentDate())
                .amount(paymentTransaction.getAmount())
                .paymentMethods(paymentMethods)
                .paymentStatus(paymentTransaction.getPaymentStatus().toString())
                .build();
    }

    public PaymentTransaction toModel(PaymentTransactionEntity entity, Long paymentId, List<PaymentMethod> paymentMethods) {
        PaymentTransaction.PaymentTransactionBuilder paymentTransactionBuilder = PaymentTransaction.builder()
                .withId(paymentId)
                .withPaymentDate(entity.getPaymentDate())
                .withAmount(entity.getAmount())
                .withPaymentMethods(paymentMethods)
                .withPaymentStatus(PaymentStatusEnum.valueOf(entity.getPaymentStatus()))
                .build().toBuilder();
        return paymentTransactionBuilder.build();
    }
}
