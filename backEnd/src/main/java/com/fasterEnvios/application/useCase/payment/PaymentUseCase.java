package com.fasterEnvios.application.useCase.payment;

import com.fasterEnvios.application.dto.payment.InvoiceResponseDTO;
import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.domain.exceptions.payment.PaymentMethodNotFoundException;
import com.fasterEnvios.domain.exceptions.shipment.ShipmentNotFoundException;
import com.fasterEnvios.application.mappers.PaymentAppMapper;
import com.fasterEnvios.application.useCase.Shipment.FindShipmentByTrackingNumber;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentUseCase {
    private final FindShipmentByTrackingNumber findShipmentByTrackingNumber;
    private final FindPaymentMethodByName findPaymentMethodByName;
    private final IPaymentRepository paymentRepository;

    public InvoiceResponseDTO execute(PaymentRequestDTO dto){
        //consultar y traer el envio por el trackingNumber
        Shipment shipmentDb = findShipmentByTrackingNumber.execute(dto.orderId())
                .orElseThrow(() -> new ShipmentNotFoundException(dto.orderId()));

        //obtengo el metodo de pago por el nombre y lo asigno
        PaymentMethod paymentMethod = findPaymentMethodByName.execute(dto.methodPaymentName())
                .orElseThrow(() -> new PaymentMethodNotFoundException(dto.methodPaymentName()));

        PaymentTransaction paymentTransaction = paymentRepository.save(PaymentTransaction.create(shipmentDb, paymentMethod, dto.payingPerson(), dto.observation()));
        return PaymentAppMapper.toDto(paymentTransaction);
    }



}
