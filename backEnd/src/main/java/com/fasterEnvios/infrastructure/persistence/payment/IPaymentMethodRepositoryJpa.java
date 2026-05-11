package com.fasterEnvios.infrastructure.persistence.payment;

import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPaymentMethodRepositoryJpa extends JpaRepository<PaymentMethodEntity, Long> {
    Optional<PaymentMethodEntity> findByName(String paymentMethodName);
}
