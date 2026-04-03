package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.useCase.Shipment.NewShipmentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final NewShipmentUseCase newShipmentUseCase;

    @PostMapping
    public ResponseEntity<NewShipmentResponseDTO> newShipment (@Valid @RequestBody NewShipmentRequestDTO dto) {
        NewShipmentResponseDTO shipmentResponseDTO = newShipmentUseCase.execute(dto);
        return ResponseEntity.ok(shipmentResponseDTO);
    }


}
