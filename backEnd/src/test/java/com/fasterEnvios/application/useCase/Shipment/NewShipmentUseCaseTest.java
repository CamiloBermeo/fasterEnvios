package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.application.dto.city.CityRequestDTO;
import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterEnvios.application.dto.packages.PackageRequestDTO;
import com.fasterEnvios.application.dto.person.PersonRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.port.out.IRouteServiceClient;
import com.fasterEnvios.application.useCase.city.FindCityByNameUseCase;
import com.fasterEnvios.application.useCase.city.SaveCityUseCase;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.repository.IShipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NewShipmentUseCaseTest {

    @Mock
    FindCityByNameUseCase findCityByName;
    @Mock
    SaveCityUseCase saveCityUseCase;
    @Mock
    IRouteServiceClient routeServiceClient;
    @Mock
    IShipmentRepository shipmentRepository;

    @InjectMocks
    NewShipmentUseCase newShipmentUseCase;
    /*
     CityDescription citySenderDB = findCityByName.execute(dto.sender().city().name())
                        .orElseGet( () -> saveCityUseCase.execute(dto.sender().city().name()));

    * */
    @Test
    void execute_whenCityExistInDb_youShouldNeverCallSaveCityUseCase() {
        NewShipmentRequestDTO dto = buildNewShipmentRequestDtoForTest();
        CityDescription city = buildCityForTest();

        when(findCityByName.execute(dto.sender().city().name()))
                .thenReturn(Optional.of(city));
        when(findCityByName.execute(dto.addressee().city().name()))
                .thenReturn(Optional.of(city));
        when(routeServiceClient.requestDistance(any()))
                .thenReturn(new ClientResponseDTO(150.0, 2.2));

        when(shipmentRepository.save(any()))
                .thenReturn(buildShipmentForTest());
        newShipmentUseCase.execute(dto);
        verify(saveCityUseCase, never()).execute(any());
    }
    @Test
    void execute_whenCityNotExistInDb_youShouldCalledSaveCityUseCase() {
        NewShipmentRequestDTO dto = buildNewShipmentRequestDtoForTest();
        CityDescription city = buildCityForTest();

        when(findCityByName.execute(dto.sender().city().name()))
                .thenReturn(Optional.empty());
        when(findCityByName.execute(dto.addressee().city().name()))
                .thenReturn(Optional.empty());
        when(saveCityUseCase.execute(dto.sender().city().name()))
                .thenReturn(city);
        when(saveCityUseCase.execute(dto.addressee().city().name()))
                .thenReturn(city);
        when(routeServiceClient.requestDistance(any()))
                .thenReturn(new ClientResponseDTO(150.0, 2.2));

        when(shipmentRepository.save(any()))
                .thenReturn(buildShipmentForTest());
        newShipmentUseCase.execute(dto);
        verify(saveCityUseCase, times(2)).execute(any());
}
    private Shipment buildShipmentForTest() {
        PackageModel packageModel = PackageModel.builder()
                .withId(11L)
                .withDimensions(1)
                .withDeclaredValue(BigDecimal.valueOf(111))
                .withDescription("MOTO")
                .build();
        CityDescription city = CityDescription.builder()
                .withId(10L)
                .withCountry("COLOMBIA")
                .withName("Cali")
                .build();
        Person sender = Person.builder()
                .withId(1L)
                .withName("Camilo")
                .withIdentityDocument("1004036028")
                .withCity(city)
                .build();
        Person addressee = Person.builder()
                .withId(2L)
                .withName("Andres")
                .withIdentityDocument("1004036029")
                .withCity(city)
                .build();
        return Shipment.builder()
                .withId(3L)
                .withTrackingNumber("TRACKING-REAL")
                .withSender(sender)
                .withAddressee(addressee)
                .withPackages(packageModel)
                .withTotalAmount(BigDecimal.valueOf(500000))
                .withState(StateEnum.RECEIVED)
                .build();
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