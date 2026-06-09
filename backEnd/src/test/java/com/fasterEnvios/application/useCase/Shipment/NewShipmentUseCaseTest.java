package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.application.dto.city.CityRequestDTO;
import com.fasterEnvios.application.dto.packages.PackageRequestDTO;
import com.fasterEnvios.application.dto.person.PersonRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.useCase.city.FindCityByNameUseCase;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.domain.model.StateEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewShipmentUseCaseTest {

    @Mock
    FindCityByNameUseCase findCityByNameUseCase;

    @InjectMocks
    NewShipmentUseCase newShipmentUseCase;
    /*
     CityDescription citySenderDB = findCityByName.execute(dto.sender().city().name())
                        .orElseGet( () -> saveCityUseCase.execute(dto.sender().city().name()));

    * */
    @Test
    void execute_whenCityExistInDb_youShouldReturnCity() {
        NewShipmentRequestDTO dto = buildNewShipmentRequestDtoForTest();
        CityDescription city = buildCityForTest();

        when(findCityByNameUseCase.execute(dto.sender().name()))
                .thenReturn(Optional.of(city));


    }

    private CityDescription buildCityForTest(){
        return CityDescription.builder()
                .withId(4L)
                .withName("Cali")
                .withLongitude(123.3)
                .withLatitude(321.1)
                .withCountry("Colombia")
                .build();
    }
    private NewShipmentRequestDTO buildNewShipmentRequestDtoForTest(){
        CityRequestDTO city = new CityRequestDTO("Cali");
        PersonRequestDTO sender = new PersonRequestDTO(
                "Antonio","perez","123","321","321cv",city
        );
        PersonRequestDTO addressee = new PersonRequestDTO(
                "Andres","pinzon","123","321","123cv",city
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