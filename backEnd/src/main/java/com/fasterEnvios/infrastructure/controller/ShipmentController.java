package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.NewShipmentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    @PostMapping
    public ResponseEntity<NewShipmentResponseDTO> newShipment (@RequestBody NewShipmentRequestDTO dto){

    }


}
