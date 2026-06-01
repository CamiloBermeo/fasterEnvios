package com.fasterEnvios.application.useCase.payment;

import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.domain.exceptions.shipment.ShipmentNotFoundException;
import com.fasterEnvios.application.useCase.Shipment.FindShipmentByTrackingNumber;
import com.fasterEnvios.domain.repository.IPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentUseCaseTest {

    // @Mock le dice a Mockito: crea un objeto falso de esta clase
    // No va a la DB real, vos controlás qué retorna
    @Mock
    private FindShipmentByTrackingNumber findShipmentByTrackingNumber;
    @Mock
    private FindPaymentMethodByName findPaymentMethodByName;
    @Mock
    private IPaymentRepository paymentRepository;
    // @InjectMocks crea el objeto real e inyecta los mocks de arriba
    // Es como si Spring hiciera la inyección de dependencias, pero en el test
    @InjectMocks
    private PaymentUseCase paymentUseCase;

    //test para cuando el tracking number no existe debe lanzar shipment not found exception
    @Test
    void execute_WhenTrackingDoesNotExist_YouShouldThrowShipmentNotFoundException() {
        //ARRANGE: preparar el escenario
        //creo de manera falsa lo que el metodo a probar recibe para verificar si funciona bien
        PaymentRequestDTO dto = new PaymentRequestDTO(
          "TRACKING-FALSO","REMITENTE","CONTRAENTREGA",""
        );

        //le digo a mock que cuando se llame a ese tracking falso, retorna un empty
        when(findShipmentByTrackingNumber.execute("TRACKING-FALSO"))
                .thenReturn(Optional.empty());

        //ACT + ASSERT: esto ejecuta el metodo verdadero y espera la respuesta correcta
        assertThrows(ShipmentNotFoundException.class, () -> {
           paymentUseCase.execute(dto);
        });
    }
}