package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.exceptions.jdbc.SaveErrorDataBaseException;
import com.fasterEnvios.application.mappers.CityAppMapper;
import com.fasterEnvios.application.mappers.ClientAppMapper;
import com.fasterEnvios.application.mappers.ShipmentAppMapper;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.PaymentStatusEnum;
import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.model.StateEnum;
import com.fasterEnvios.domain.repository.CityRepository;
import com.fasterEnvios.domain.repository.ShipmentRepository;
import com.fasterEnvios.infrastructure.client.OpenRoutServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NewShipmentUseCase {

    private final OpenRoutServiceClient openRoutServiceClient;
    private final FindCityByNameUseCase findCityByNameUseCase;
    private final CityRepository cityRepository;
    private final ShipmentRepository shipmentRepository;

    public NewShipmentResponseDTO execute(NewShipmentRequestDTO dto) throws IOException, InterruptedException {

        CityDescription cityOriginDB = findCityByNameUseCase.execute(dto.cityOrigin())
                .orElseGet(() -> {
                    try {
                        return saveCityWhenIsEmpty(dto.cityOrigin());
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
        CityDescription cityDestinationDB = findCityByNameUseCase.execute(dto.cityDestination())
                .orElseGet(() -> {
                    try {
                        return saveCityWhenIsEmpty(dto.cityOrigin());
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        ClientRequestDTO client = ClientAppMapper.toClient(cityOriginDB, cityDestinationDB);
        ClientResponseDTO info = openRoutServiceClient.requestDistance(client);
        LocalDateTime estimatedDeliveryDate = LocalDateTime.now().plusDays(3);
        StateEnum state = dto.status();
// solucionar tema de pago
        if (state == null){
            state = StateEnum.RECEIVED;
        }
        Shipment shipment = ShipmentAppMapper.toModel(dto, estimatedDeliveryDate, info.distance(), state, totalAmount);

        Shipment savedShipment = shipmentRepository.save(shipment);

        return ShipmentAppMapper.toDto(savedShipment);
    }




    private CityDescription saveCityWhenIsEmpty(String city) throws IOException, InterruptedException {
        String country = "Colombia";
        CityCoordinatesRequestDTO cityForClient = CityAppMapper.toClientCityCoordinates(city, country);
        CityCoordinatesResponseDTO coordinates = openRoutServiceClient.requestCoordinates(cityForClient);
        return cityRepository.save(CityAppMapper.toDomain(city, coordinates))
                .orElseThrow(() -> new SaveErrorDataBaseException(city));
    }
}
