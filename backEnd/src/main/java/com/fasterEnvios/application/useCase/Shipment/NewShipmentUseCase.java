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
import com.fasterEnvios.application.mappers.ShipmentAppMapper;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.model.StateEnum;
import com.fasterEnvios.domain.repository.ICityRepository;
import com.fasterEnvios.domain.repository.IShipmentRepository;
import com.fasterEnvios.infrastructure.client.OpenRoutServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewShipmentUseCase {

    private final OpenRoutServiceClient openRoutServiceClient;
    private final FindCityByNameUseCase findCityByNameUseCase;
    private final ICityRepository ICityRepository;
    private final IShipmentRepository IShipmentRepository;

    public NewShipmentResponseDTO execute(NewShipmentRequestDTO dto) {
        //primero busco la ciudad si esta en la base de datos
        CityDescription citySenderDB = findCityByNameUseCase.execute(dto.sender().city().name())
                .orElseGet(() -> {
                 //en caso de que no este en la db hago llamada a la api para buscarla y traer informacion de esta ciudad
                        return saveCityWhenIsEmpty(dto.sender().city().name());

                });
        CityDescription cityAddresseeDB = findCityByNameUseCase.execute(dto.addressee().city().name())
                .orElseGet(() -> {

                        return saveCityWhenIsEmpty(dto.addressee().city().name());

                });
        //una vez ya tenga las ciudads en orden consulto al cliente ara la distancia entre las ciudades
        ClientRequestDTO client = ClientAppMapper.toClient(citySenderDB, cityAddresseeDB);
        ClientResponseDTO info = openRoutServiceClient.requestDistance(client);
        LocalDateTime estimatedDeliveryDate = LocalDateTime.now().plusDays(3);
        StateEnum state = dto.status();
        if (state == null) {
            state = StateEnum.RECEIVED;
        }
        BigDecimal totalAmount = calculatedTotalAmount(info.distance(), dto.packages().weightKg(), dto.packages().declaredValue());
        //armo en el mapper toda la informacion del envio y lo pongo en la clase shipment
        Shipment shipment = ShipmentAppMapper.toModel(dto, estimatedDeliveryDate, info.distance(), state, totalAmount, citySenderDB, cityAddresseeDB);
        //mando a guardar el envio en el repository
        Shipment savedShipment = IShipmentRepository.save(shipment);
        //retorno el dto con la informacion necesaria del shipment
        return ShipmentAppMapper.toDto(savedShipment);
    }


    private CityDescription saveCityWhenIsEmpty(String city) {
        String country = "Colombia";
        //creo el dto con la ciudad y el pais con el mapper
        CityCoordinatesRequestDTO cityForClient = CityAppMapper.toClientCityCoordinates(city, country);
        //envio ese dto al cliente y recibo un dto con la informacion de la ciudad
        CityCoordinatesResponseDTO coordinates = openRoutServiceClient.requestCoordinates(cityForClient);
        //acá paso la informacion obtenida en el cliente para guardarla en la base de datos
        return ICityRepository.save(CityAppMapper.toDomain(city, country, coordinates));
    }

    private BigDecimal calculatedTotalAmount(double distance, double weight, BigDecimal declaredValue) {
        BigDecimal percentage = new BigDecimal("0.05");
        BigDecimal costDistance = BigDecimal.valueOf(distance * 20);
        BigDecimal costWeight = BigDecimal.valueOf(weight * 100);
        BigDecimal secure = percentage.multiply(declaredValue);
        return costDistance.add(costWeight).add(secure);
    }
}
