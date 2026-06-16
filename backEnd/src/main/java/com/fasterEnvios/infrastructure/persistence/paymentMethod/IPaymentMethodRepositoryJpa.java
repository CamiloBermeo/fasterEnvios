package com.fasterEnvios.infrastructure.persistence.paymentMethod;

import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPaymentMethodRepositoryJpa extends JpaRepository<PaymentMethodEntity, Long> {
    Optional<PaymentMethodEntity> findByName(String paymentMethodName);
}
