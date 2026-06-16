package com.fasterEnvios.infrastructure.persistence.paymentMethod;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.repository.IPaymentMethodRepository;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import com.fasterEnvios.infrastructure.mapper.PaymentMethodInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentMethodRepositoryJpaAdapter implements IPaymentMethodRepository {
    private final IPaymentMethodRepositoryJpa jpa;

    @Override
    public Optional<PaymentMethod> findPaymentMethodByName(String paymentMethodName) {
        Optional<PaymentMethodEntity> entity = jpa.findByName(paymentMethodName);
        return entity.map(PaymentMethodInfraMapper::toModel);
    }
    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        PaymentMethodEntity PaymentMethodEntity = PaymentMethodInfraMapper.toEntity(paymentMethod);
        return PaymentMethodInfraMapper.toModel(jpa.save(PaymentMethodEntity));
    }
}
