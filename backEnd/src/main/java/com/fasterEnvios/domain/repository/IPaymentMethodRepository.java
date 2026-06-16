package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.PaymentMethod;

import java.util.Optional;

public interface IPaymentMethodRepository {
    PaymentMethod save (PaymentMethod paymentMethod);
    Optional<PaymentMethod> findPaymentMethodByName(String paymentMethodName);
}
