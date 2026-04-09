package com.fasterEnvios.infrastructure.mapper;

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
                .paymentDate(paymentTransaction.getPaymentDate())
                .amount(paymentTransaction.getAmount())
                .paymentMethod(PaymentMethodInfraMapper.toEntity(paymentTransaction.getPaymentMethods()))
                .paymentStatus(paymentTransaction.getPaymentStatus().toString())
                .build();
    }

    public static PaymentTransaction toModel(PaymentTransactionEntity entity) {
        PaymentTransaction.PaymentTransactionBuilder paymentTransactionBuilder = PaymentTransaction.builder()
                .withId(entity.getId())
                .withPaymentDate(entity.getPaymentDate())
                .withAmount(entity.getAmount())
                .withPaymentMethods(PaymentMethodInfraMapper.toModel(entity.getPaymentMethod()))
                .withPaymentStatus(PaymentStatusEnum.valueOf(entity.getPaymentStatus()))
                .build().toBuilder();
        return paymentTransactionBuilder.build();
    }
}
