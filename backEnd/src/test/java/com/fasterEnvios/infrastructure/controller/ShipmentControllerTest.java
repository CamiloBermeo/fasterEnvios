package com.fasterEnvios.infrastructure.controller;

import com.fasterEnvios.application.dto.city.CityRequestDTO;
import com.fasterEnvios.application.dto.packages.PackageRequestDTO;
import com.fasterEnvios.application.dto.person.PersonRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.useCase.Shipment.NewShipmentUseCase;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.domain.model.StateEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShipmentControllerTest {
    @Mock
    NewShipmentUseCase newShipmentUseCase;

    @InjectMocks
    ShipmentController shipmentController;

    @Test
    void newShipment_whenIsSuccess_youShouldGenerateDtoResponse() {
        NewShipmentRequestDTO dto = buildNewShipmentRequestDtoForTest();
        NewShipmentResponseDTO response = buildNewShipmentResponseDtoForTest();
        when(newShipmentUseCase.execute(any()))
                .thenReturn(response);

        ResponseEntity<NewShipmentResponseDTO> result = shipmentController.newShipment(dto);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    private NewShipmentResponseDTO buildNewShipmentResponseDtoForTest() {
        return new NewShipmentResponseDTO(
                "TRACKING_TEST",
                "STATUS_TEST",
                "CIUDAD_ORIGEN_TEST",
                "CIUDAD_DESTINO_TEST",
                "123.4",
                "123",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private NewShipmentRequestDTO buildNewShipmentRequestDtoForTest() {
        CityRequestDTO city = new CityRequestDTO("Cali");
        PersonRequestDTO sender = new PersonRequestDTO(
                "Antonio",
                "perez",
                "123",
                "321",
                "321cv",
                city
        );
        PersonRequestDTO addressee = new PersonRequestDTO(
                "Andres",
                "pinzon",
                "123",
                "321",
                "123cv",
                city
        );
        PackageRequestDTO packages = new PackageRequestDTO(
                3.2,
                23.3,
                BigDecimal.valueOf(1234.3),
                "efecty");
        StateEnum state = StateEnum.RECEIVED;
        return new NewShipmentRequestDTO(
                sender,
                addressee,
                packages,
                state
        );
    }

}