package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.ClientRequestDTO;
import com.fasterEnvios.domain.model.CityDescription;

import java.util.ArrayList;
import java.util.List;

public class ClientAppMapper {


    public static ClientRequestDTO toClient(CityDescription cityOrigin, CityDescription cityDestination) {


        List<double[]> coordinates = List.of(
                new double[]{cityOrigin.getLongitude(), cityOrigin.getLatitude()},
                new double[]{cityDestination.getLongitude(), cityDestination.getLatitude()}
        );




        return new ClientRequestDTO(
            coordinates,
                "km",
                "es"
        );

    }



}
