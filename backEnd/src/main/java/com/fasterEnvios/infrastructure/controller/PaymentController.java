package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.application.dto.payment.InvoiceResponseDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.useCase.payment.PaymentUseCase;
import com.fasterEnvios.infrastructure.docs.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private PaymentUseCase paymentUseCase;

    @PostMapping("payment")
    @Operation(
            summary = "Registrar pago de envío",
            description = """
                    Este endpoint permite procesar y registrar el pago asociado a un envío existente (orden).
                    Valida el monto, el método de pago (ej. Efectivo/Contraentrega) y el responsable del cobro.
                    Al procesarse correctamente, genera y retorna la factura o comprobante correspondiente.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = StatusCode.OK,
                            description = StatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PaymentRequestDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<InvoiceResponseDTO> newPayment(@Valid @RequestBody PaymentRequestDTO dto){
        InvoiceResponseDTO invoice = paymentUseCase.execute(dto);
        return ResponseEntity.ok(invoice);
    }

}
