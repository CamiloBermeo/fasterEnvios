package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.dto.user.UserResponseDTO;
import com.fasterEnvios.application.useCase.Shipment.NewShipmentUseCase;
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

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    private final NewShipmentUseCase newShipmentUseCase;

    @PostMapping("newShipment")
    @Operation(
            summary = "Crea un nuevo envío",
            description = """
                    Este endpoint permite registrar un nuevo envío en el sistema a partir de la información proporcionada
                    en el cuerpo de la solicitud. Procesa los datos del remitente, destinatario y detalles del paquete,
                    generando un registro de envío con su respectiva información asociada.
                    
                    Retorna los datos del envío creado, incluyendo su identificador y estado inicial.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = StatusCode.OK,
                            description = StatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewShipmentRequestDTO.class)
                            )
                    )
            }
    )
    public ResponseEntity<NewShipmentResponseDTO> newShipment(@Valid @RequestBody NewShipmentRequestDTO dto) {
        NewShipmentResponseDTO shipmentResponseDTO = newShipmentUseCase.execute(dto);
        return ResponseEntity.ok(shipmentResponseDTO);
    }


}
