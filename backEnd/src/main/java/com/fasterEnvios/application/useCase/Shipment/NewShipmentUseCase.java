package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.mappers.CityAppMapper;
import com.fasterEnvios.application.mappers.ClientAppMapper;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.CityRepository;
import com.fasterEnvios.infrastructure.client.OpenRoutServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewShipmentUseCase {

    private final OpenRoutServiceClient openRoutServiceClient;
    private final FindCityByNameUseCase findCityByNameUseCase;
    private final CityRepository cityRepository;

    public NewShipmentResponseDTO execute(NewShipmentRequestDTO dto) {

        CityDescription cityOriginDB = findCityByNameUseCase.execute(dto.cityOrigin())
                .orElseGet(()-> saveCityWhenIsEmpty(dto.cityOrigin()));

        CityDescription cityDestinationDB = findCityByNameUseCase.execute(dto.cityDestination())
                .orElseGet(()-> saveCityWhenIsEmpty(dto.cityOrigin()));

        ClientRequestDTO client = ClientAppMapper.toClient(cityOriginDB, cityDestinationDB);

        ClientResponseDTO info = openRoutServiceClient.requestDistance(client);

        return null;
    }

    private CityDescription saveCityWhenIsEmpty(String city) {
        String country = "Colombia";
        CityCoordinatesRequestDTO cityForClient = CityAppMapper.toClientCityCoordinates(city, country);
        CityCoordinatesResponseDTO coordinates = openRoutServiceClient.requestCoordinates(cityForClient);
        return cityRepository.save(CityAppMapper.toDomain(city, coordinates));
    }
}
