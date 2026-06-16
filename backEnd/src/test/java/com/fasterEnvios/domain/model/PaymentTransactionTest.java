package com.fasterEnvios.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentTransactionTest {

    @InjectMocks
    private PaymentTransaction paymentTransaction;

    @Test
    void create_whenStatusIsPending() {
        Shipment shipment = buildShipmentForTest();
        PaymentMethod paymentMethod = buildPaymentMethodForTest();

        PaymentTransaction payment = PaymentTransaction.create(shipment, paymentMethod, "DESTINATARIO", "observation");
        assertEquals(payment.getPaymentStatus().toString(),PaymentStatusEnum.PENDING.toString());
    }
    @Test
    void create_whenMethodNameIsContraentrega() {
        Shipment shipment = buildShipmentForTest();
        PaymentMethod paymentMethod = buildPaymentMethodForTest();

        PaymentTransaction payment = PaymentTransaction.create(shipment, paymentMethod, "REMITENTE", "observation");
        assertEquals(payment.getPaymentStatus().toString(),PaymentStatusEnum.PENDING.toString());
    }



    private PaymentMethod buildPaymentMethodForTest() {
        return PaymentMethod.builder()
                .withId(123L)
                .withMethodName("CONTRAENTREGA")
                .withStatus(true)
                .build();
    }

    private Shipment buildShipmentForTest() {
        PackageModel packageModel = PackageModel.builder()
                .withId(11L)
                .withDimensions(1)
                .withDeclaredValue(BigDecimal.valueOf(111))
                .withDescription("MOTO")
                .build();
        CityDescription city = CityDescription.builder()
                .withId(10L)
                .withCountry("COLOMBIA")
                .withName("Cali")
                .build();
        Person sender = Person.builder()
                .withId(1L)
                .withName("Camilo")
                .withIdentityDocument("1004036028")
                .withCity(city)
                .build();
        Person addressee = Person.builder()
                .withId(2L)
                .withName("Andres")
                .withIdentityDocument("1004036029")
                .withCity(city)
                .build();
        return Shipment.builder()
                .withId(3L)
                .withTrackingNumber("TRACKING-REAL")
                .withSender(sender)
                .withAddressee(addressee)
                .withPackages(packageModel)
                .withTotalAmount(BigDecimal.valueOf(500000))
                .withState(StateEnum.RECEIVED)
                .build();
    }

}