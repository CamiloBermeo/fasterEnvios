package com.fasterEnvios.infrastructure.persistence.payment;

import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepositoryJpa extends JpaRepository<PaymentTransactionEntity, Long> {
}
