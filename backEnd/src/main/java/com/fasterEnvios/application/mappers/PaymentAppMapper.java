package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.domain.model.*;

import java.time.LocalDateTime;

public class PaymentAppMapper {

    public static PaymentTransaction toModel (PaymentRequestDTO dto,
                                              Shipment shipment,
                                              Person payingPerson,
                                              PaymentMethod paymentMethod,
                                              PaymentStatusEnum paymentStatus) {
        return PaymentTransaction.builder()
                .withShipments(shipment)
                .withPayingPerson(payingPerson)
                .withPaymentMethods(paymentMethod)
                .withAmount(dto.amount())
                .withPaymentDate(LocalDateTime.now())
                .withPaymentStatus(paymentStatus)
                .withObservation(dto.observation())
                .build();


    }

}
