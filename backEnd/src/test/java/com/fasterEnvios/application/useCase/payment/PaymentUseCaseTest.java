package com.fasterEnvios.application.useCase.payment;

import com.fasterEnvios.application.dto.payment.InvoiceResponseDTO;
import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.domain.exceptions.payment.PaymentMethodNotFoundException;
import com.fasterEnvios.domain.exceptions.shipment.ShipmentNotFoundException;
import com.fasterEnvios.application.useCase.Shipment.FindShipmentByTrackingNumber;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.repository.IPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
                "TRACKING-FALSO", "REMITENTE", "CONTRAENTREGA", ""
        );

        //le digo a mock que cuando se llame a ese tracking falso, retorna un empty
        when(findShipmentByTrackingNumber.execute("TRACKING-FALSO"))
                .thenReturn(Optional.empty());

        //ACT + ASSERT: esto ejecuta el metodo verdadero y espera la respuesta correcta
        assertThrows(ShipmentNotFoundException.class, () -> {
            paymentUseCase.execute(dto);
        });
    }

    @Test
    void execute_whenShipmentExist_YouShouldReturnPaymentTransaction() {

        PaymentRequestDTO dto = new PaymentRequestDTO(
                "TRACKING-FALSO", "REMITENTE", "CONTRAENTREGA", ""
        );
        Shipment shipment = buildShipmentForTest();
        PaymentTransaction savedTransaction = buildPaymentTransaction();
        PaymentMethod paymentMethod = PaymentMethod.builder().withId(8L)
                .withMethodName("METHOD_ONE")
                .build();
        when(findShipmentByTrackingNumber.execute("TRACKING-FALSO"))
                .thenReturn(Optional.of(shipment));
        when(findPaymentMethodByName.execute("CONTRAENTREGA"))
                .thenReturn(Optional.of(paymentMethod));
        when(paymentRepository.save(any(PaymentTransaction.class)))
                .thenReturn(savedTransaction);

        InvoiceResponseDTO result = paymentUseCase.execute(dto);
        assertNotNull(result);
    }
    @Test
    void execute_whenPaymentMethodNotExist_youShouldThrowPaymentMethodNotFoundException() {
        PaymentRequestDTO dto = new PaymentRequestDTO(
                "TRACKING-REAL", "REMITENTE", "PAYPAL", ""
        );
        when(findShipmentByTrackingNumber.execute("TRACKING-REAL"))
                .thenReturn(Optional.of(buildShipmentForTest()));
        when(findPaymentMethodByName.execute("PAYPAL"))
                .thenReturn(Optional.empty());
        assertThrows(PaymentMethodNotFoundException.class, () -> {
            paymentUseCase.execute(dto);
        });
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
                .build();
    }

    private PaymentTransaction buildPaymentTransaction(){
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .withId(8L)
                .withMethodName("METHOD_ONE")
                .build();
        PaymentStatusEnum paymentStatusEnum = PaymentStatusEnum.CANCELED;
        return PaymentTransaction.builder()
                .withId(5L)
                .withShipments(buildShipmentForTest())
                .withPaymentMethods(paymentMethod)
                .withPaymentStatus(paymentStatusEnum)
                .build();
    }
}