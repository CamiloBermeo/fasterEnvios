package com.fasterEnvios.application.port.out;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.application.dto.client.ClientResponseDTO;

public interface IRouteServiceClient {
    ClientResponseDTO requestDistance(ClientRequestDTO request);
    CityCoordinatesResponseDTO requestCoordinates(CityCoordinatesRequestDTO request);
}
