package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.mappers.ClientAppMapper;
import com.fasterEnvios.application.mappers.ShipmentAppMapper;
import com.fasterEnvios.application.useCase.city.SaveCityUseCase;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.model.StateEnum;
import com.fasterEnvios.domain.repository.IShipmentRepository;
import com.fasterEnvios.infrastructure.client.OpenRouteServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewShipmentUseCase {

    private final OpenRouteServiceClient openRouteServiceClient;
    private final SaveCityUseCase saveCityUseCase;
    private final IShipmentRepository IShipmentRepository;

    public NewShipmentResponseDTO execute(NewShipmentRequestDTO dto) {
        //primero busco la ciudad si esta en la base de datos
        CityDescription citySenderDB = saveCityUseCase.execute(dto.sender().city().name());
        CityDescription cityAddresseeDB = saveCityUseCase.execute(dto.addressee().city().name());

        //una vez ya tenga las ciudades en orden consulto al cliente para la distancia entre las ciudades
        ClientResponseDTO info = openRouteServiceClient.requestDistance(ClientAppMapper.toClient(citySenderDB, cityAddresseeDB));
        LocalDateTime estimatedDeliveryDate = LocalDateTime.now().plusDays(3);
        StateEnum state = stateShipment(dto.state());
        String trackingNumber = UUID.randomUUID().toString();
        BigDecimal totalAmount = calculatedTotalAmount(info.distance(), dto.packages().weightKg(), dto.packages().declaredValue());

        //armo en el mapper toda la informacion del envio y lo pongo en la clase shipment
        Shipment shipment = ShipmentAppMapper.toModel(dto,trackingNumber, estimatedDeliveryDate, info.distance(), state, totalAmount, citySenderDB,cityAddresseeDB);
        //mando a guardar el envio en el repository
        Shipment savedShipment = IShipmentRepository.save(shipment);
        return ShipmentAppMapper.toDto(savedShipment);
    }

    private BigDecimal calculatedTotalAmount(double distance, double weight, BigDecimal declaredValue) {
        BigDecimal percentage = new BigDecimal("0.05");
        BigDecimal costDistance = BigDecimal.valueOf(distance * 20);
        BigDecimal costWeight = BigDecimal.valueOf(weight * 100);
        BigDecimal secure = percentage.multiply(declaredValue);
        return costDistance.add(costWeight).add(secure);
    }

    private StateEnum stateShipment(StateEnum state){
        if (state == null) {
            return state = StateEnum.RECEIVED;
        }
        return state;
    }
}
