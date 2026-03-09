package com.fasterEnvios.infrastructure.client;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class OpenRoutServiceClient {

    public ClientResponseDTO requestDistance (ClientRequestDTO dto)throws JsonProcessingException {
        String encodeTile = URLEncoder.encode(dto, StandardCharsets.UTF_8);
        String url =  "https://api.openrouteservice.org/v2/directions/driving-car";



    }

    public CityCoordinatesResponseDTO requestCoordinates (CityCoordinatesRequestDTO dto){

    }

}
