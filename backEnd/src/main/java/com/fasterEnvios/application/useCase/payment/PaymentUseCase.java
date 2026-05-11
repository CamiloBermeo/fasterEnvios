package com.fasterEnvios.application.useCase.payment;

import com.fasterEnvios.application.dto.payment.InvoiceResponseDTO;
import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.application.exceptions.payment.PaymentMethodNotFoundException;
import com.fasterEnvios.application.exceptions.shipment.ShipmentNotFoundException;
import com.fasterEnvios.application.mappers.PaymentAppMapper;
import com.fasterEnvios.application.useCase.Shipment.FindShipmentByTrackingNumber;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        //obtener informacion de la persona que paga
        Person payingPerson = dto.payingPerson().equals("DESTINATARIO") ? shipmentDb.getAddressee(): shipmentDb.getSender() ;
        //obtengo el metodo de pago por el nombre y lo asigno
        PaymentMethod paymentMethod = findPaymentMethodByName.execute(dto.methodPaymentName())
                .orElseThrow(() -> new PaymentMethodNotFoundException(dto.methodPaymentName()));

        //estado del pago
        PaymentStatusEnum paymentStatus = statusAssignment(dto.methodPaymentName(), dto.payingPerson());

        PaymentTransaction paymentTransaction = paymentRepository.save(PaymentAppMapper.toModel(dto,shipmentDb,payingPerson, paymentMethod, paymentStatus ));

    }

    private PaymentStatusEnum statusAssignment(String methodPaymentName, String payingPerson){
        if(payingPerson.equals("DESTINATARIO") || methodPaymentName.equals("CONTRAENTREGA")){
            return PaymentStatusEnum.PENDING;
        }else{
            return PaymentStatusEnum.PAYMENT;
        }
    }

}
