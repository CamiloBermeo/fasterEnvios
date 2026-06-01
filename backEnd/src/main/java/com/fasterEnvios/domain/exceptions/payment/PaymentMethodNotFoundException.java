package com.fasterEnvios.domain.exceptions.payment;

public class PaymentMethodNotFoundException extends RuntimeException {
    public PaymentMethodNotFoundException(String paymentMethodName) {
        super("El metodo de pago: "+paymentMethodName+ " no se encuentra registrado");
    }
}
