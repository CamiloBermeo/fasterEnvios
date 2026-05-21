package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.payment.InvoiceResponseDTO;
import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.domain.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentAppMapper {

    public static PaymentTransaction toModel (PaymentRequestDTO dto,
                                              Shipment shipment,
                                              Person payingPerson,
                                              PaymentMethod paymentMethod,
                                              PaymentStatusEnum paymentStatus,
                                              String idTransaction) {
        return PaymentTransaction.builder()
                .withIdTransaction(idTransaction)
                .withShipments(shipment)
                .withPayingPerson(payingPerson)
                .withPaymentMethods(paymentMethod)
                .withAmount(shipment.getTotalAmount())
                .withPaymentDate(LocalDateTime.now())
                .withPaymentStatus(paymentStatus)
                .withObservation(dto.observation())
                .build();
    }

    public static InvoiceResponseDTO toDto(PaymentTransaction paymentTransaction) {
        return new InvoiceResponseDTO(
                paymentTransaction.getShipments().getTrackingNumber(),
                paymentTransaction.getShipments().getSender().getName(),
                paymentTransaction.getShipments().getSender().getAddress(),
                paymentTransaction.getShipments().getSender().getCity().getName(),
                paymentTransaction.getShipments().getAddressee().getName(),
                paymentTransaction.getShipments().getAddressee().getAddress(),
                paymentTransaction.getShipments().getAddressee().getCity().getName(),
                paymentTransaction.getShipments().getPackageModels().getDescription(),
                paymentTransaction.getShipments().getPackageModels().getWeightKg(),
                paymentTransaction.getShipments().getEstimatedDeliveryDate(),
                paymentTransaction.getIdTransaction(),
                paymentTransaction.getPaymentMethods().getMethodName(),
                paymentTransaction.getPaymentDate(),
                paymentTransaction.getPaymentStatus().toString(),
                LocalDateTime.now().toString()

        );
    }
}
