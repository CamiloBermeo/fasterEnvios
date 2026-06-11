package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.payment.InvoiceResponseDTO;
import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.application.useCase.payment.PaymentUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {
    @Mock
    PaymentUseCase paymentUseCase;
    @InjectMocks
    PaymentController paymentController;

    @Test
    void newPayment_whenIsSuccess_youShouldGenerateDtoResponse() {
        PaymentRequestDTO dto = buildPaymentRequestDtoForTest();
        InvoiceResponseDTO response = buildInvoiceResponseDtoForTest();
        when(paymentUseCase.execute(any()))
                .thenReturn(response);
        ResponseEntity<InvoiceResponseDTO> responseEntity = paymentController.newPayment(dto);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
    }

    private InvoiceResponseDTO buildInvoiceResponseDtoForTest() {
        return new InvoiceResponseDTO(
                "TRK-2026-0001",                    // trackingNumber
                "Juan Camilo Perez",                // senderName
                "Calle 10 #15-20",                  // senderAddress
                "Cali",                             // senderCity
                "Maria Gonzalez",                   // addresseeName
                "Carrera 25 #30-45",                // addresseeAddress
                "Bogota",                           // addresseeCity
                "Laptop Lenovo ThinkPad",           // packageDescription
                1.4,                                // packageWeight
                LocalDateTime.of(2026, 6, 15, 14, 0), // estimateDeliveryDate
                "TXN-987654321",                    // idTransaction
                "CREDIT_CARD",                      // paymentMethod
                LocalDateTime.of(2026, 6, 11, 10, 30), // paymentDate
                "PAID",                             // paymentStatus
                "2026-06-11"                        // InvoiceIssueDate
        );
    }

    private PaymentRequestDTO buildPaymentRequestDtoForTest() {
        return new PaymentRequestDTO(
                "ORDER_TEST",
                "PAYINGPERSON_TEST",
                "METODO_TEST",
                "OBSERVACION_TEST"
        );
    }
}