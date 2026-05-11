package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.model.PaymentTransaction;

import java.util.Optional;

public interface IPaymentRepository {
    Optional<PaymentMethod> findPaymentMethodByName(String paymentMethodName);
    PaymentTransaction save(PaymentTransaction paymentTransaction);
}
