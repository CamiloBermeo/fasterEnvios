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
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.CityRepository;
import com.fasterEnvios.infrastructure.client.OpenRoutServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NewShipmentUseCase {

    private final OpenRoutServiceClient openRoutServiceClient;
    private final FindCityByNameUseCase findCityByNameUseCase;
    private final CityRepository cityRepository;

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

//despues de verificar si las ciudades existen y en caso de que no guardarla, ahora debo conocer la distancia
// y el tiempo aproximado de viaje

        ClientRequestDTO client = ClientAppMapper.toClient(cityOriginDB, cityDestinationDB);
        ClientResponseDTO info = openRoutServiceClient.requestDistance(client);

        return null;
    }

    private CityDescription saveCityWhenIsEmpty(String city) throws IOException, InterruptedException {
        String country = "Colombia";
        CityCoordinatesRequestDTO cityForClient = CityAppMapper.toClientCityCoordinates(city, country);
        CityCoordinatesResponseDTO coordinates = openRoutServiceClient.requestCoordinates(cityForClient);
        return cityRepository.save(CityAppMapper.toDomain(city, coordinates))
                .orElseThrow(() -> new SaveErrorDataBaseException(city));
    }
}
