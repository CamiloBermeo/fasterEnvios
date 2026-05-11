package com.fasterEnvios.application.useCase.payment;

import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindPaymentMethodByName {

    private final IPaymentRepository paymentRepository;

public Optional<PaymentMethod> execute(String paymentMethodName){
    return paymentRepository.findPaymentMethodByName(paymentMethodName);
}

}
