package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.payment.PaymentRequestDTO;
import com.fasterEnvios.application.dto.payment.InvoiceResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<InvoiceResponseDTO> newPayment(@Valid @RequestBody PaymentRequestDTO dto){
        InvoiceResponseDTO invoice = paymentUseCase.execute(dto);
        return ResponseEntity.ok(invoice);
    }

}
