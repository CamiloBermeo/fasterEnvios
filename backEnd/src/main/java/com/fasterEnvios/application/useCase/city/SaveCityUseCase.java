package com.fasterEnvios.application.useCase.city;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.mappers.CityAppMapper;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.ICityRepository;
import com.fasterEnvios.infrastructure.client.OpenRouteServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCityUseCase {
    private final OpenRouteServiceClient openRouteServiceClient;
    private final ICityRepository cityRepository;

    public CityDescription execute(String city){
        String country = "Colombia";
        //creo el dto con la ciudad y el pais con el mapper
        CityCoordinatesRequestDTO cityForClient = CityAppMapper.toClientCityCoordinates(city, country);
        //envio ese dto al cliente y recibo un dto con la informacion de la ciudad
        CityCoordinatesResponseDTO coordinates = openRouteServiceClient.requestCoordinates(cityForClient);
        //acá paso la informacion obtenida en el cliente para guardarla en la base de datos
        return cityRepository.save(CityAppMapper.toDomain(city, country, coordinates));
    }
}
