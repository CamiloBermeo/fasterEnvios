package com.fasterEnvios.infrastructure.persistence.payment;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.model.PaymentTransaction;
import com.fasterEnvios.domain.repository.IPaymentRepository;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import com.fasterEnvios.infrastructure.mapper.PaymentMethodInfraMapper;
import com.fasterEnvios.infrastructure.mapper.PaymentTransactionInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryJpaAdapter implements IPaymentRepository {
    private final IPaymentRepositoryJpa jpa;
    private final IPaymentMethodRepositoryJpa jpaMethod;

    @Override
    public Optional<PaymentMethod> findPaymentMethodByName(String paymentMethodName) {
        Optional<PaymentMethodEntity> entity = jpaMethod.findByName(paymentMethodName);
        return entity.map(PaymentMethodInfraMapper::toModel);
    }

    @Override
    public PaymentTransaction save(PaymentTransaction paymentTransaction) {
        PaymentTransactionEntity entity = PaymentTransactionInfraMapper.toEntity(paymentTransaction);
        return PaymentTransactionInfraMapper.toModel(jpa.save(entity));
    }
}
