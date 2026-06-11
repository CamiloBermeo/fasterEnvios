package com.fasterEnvios.application.useCase.city;

import com.fasterEnvios.application.dto.city.CityRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesRequestDTO;
import com.fasterEnvios.application.dto.client.CityCoordinatesResponseDTO;
import com.fasterEnvios.application.port.out.IRouteServiceClient;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.repository.ICityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveCityUseCaseTest {

    @Mock
    IRouteServiceClient routeServiceClient;

    @Mock
    ICityRepository cityRepository;

    @InjectMocks
    SaveCityUseCase saveCityUseCase;

    @Test
    void execute_whenCityNameIsProvided_shouldReturnSavedCityDescription() {

        CityCoordinatesRequestDTO requestDTO = new CityCoordinatesRequestDTO("Cali", "Colombia");
        CityCoordinatesResponseDTO responseDTO = new CityCoordinatesResponseDTO(12.21, 13.87);
        CityDescription city = buildCityForTest();
        when(routeServiceClient.requestCoordinates(requestDTO))
                .thenReturn(responseDTO);

        when(cityRepository.save(any(CityDescription.class)))
                .thenReturn(city);

        CityDescription cityDescription = saveCityUseCase.execute("Cali");
        assertNotNull(cityDescription);

    }

    private CityDescription buildCityForTest() {
        return CityDescription.builder()
                .withId(1L)
                .withName("Cali")
                .withCountry("Colombia")
                .withLatitude(12.34)
                .withLongitude(13.56)
                .build();
    }

}