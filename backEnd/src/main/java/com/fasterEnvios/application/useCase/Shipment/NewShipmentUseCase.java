package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.mappers.client.ClientAppMapper;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.infrastructure.client.OpenRoutServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewShipmentUseCase {

    private OpenRoutServiceClient openRoutServiceClient;
    private FindCityByNameUseCase findCityByNameUseCase;

    public NewShipmentResponseDTO execute(NewShipmentRequestDTO dto){

        Optional<CityDescription> cityOriginDB = findCityByNameUseCase.execute(dto.cityOrigin())
                .orElseThrow(new );
        Optional<CityDescription> cityDestinationDB = findCityByNameUseCase.execute(dto.cityDestination());



        ClientRequestDTO client = ClientAppMapper.toClient(cityOriginDB.getCoordinates(), cityDestinationDB.getCoordinates());

        String info = openRoutServiceClient.requestDistance()

        return null;
    }

}
