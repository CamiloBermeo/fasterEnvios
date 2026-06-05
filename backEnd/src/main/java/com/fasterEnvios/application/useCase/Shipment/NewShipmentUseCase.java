package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.application.dto.client.ClientResponseDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.application.mappers.ClientAppMapper;
import com.fasterEnvios.application.mappers.PackageAppMapper;
import com.fasterEnvios.application.mappers.PersonAppMapper;
import com.fasterEnvios.application.mappers.ShipmentAppMapper;
import com.fasterEnvios.application.port.out.IRouteServiceClient;
import com.fasterEnvios.application.useCase.city.FindCityByNameUseCase;
import com.fasterEnvios.application.useCase.city.SaveCityUseCase;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.repository.IShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewShipmentUseCase {

    private final IRouteServiceClient routeServiceClient;
    private final FindCityByNameUseCase findCityByName;
    private final SaveCityUseCase saveCityUseCase;
    private final IShipmentRepository IShipmentRepository;

    public NewShipmentResponseDTO execute(NewShipmentRequestDTO dto) {
        //primero busco la ciudad si esta en la base de datos
        CityDescription citySenderDB = findCityByName.execute(dto.sender().city().name())
                        .orElseGet( () -> saveCityUseCase.execute(dto.sender().city().name()));

        CityDescription cityAddresseeDB = findCityByName.execute(dto.addressee().city().name())
                .orElseGet( () -> saveCityUseCase.execute(dto.addressee().city().name()));
        Person sender = PersonAppMapper.toModel(dto.sender(), citySenderDB);
        Person addressee = PersonAppMapper.toModel(dto.addressee(), cityAddresseeDB);
        PackageModel packageModel = PackageAppMapper.toModel(dto.packages());
        //una vez ya tenga las ciudades en orden consulto al cliente para la distancia entre las ciudades
        ClientResponseDTO info = routeServiceClient.requestDistance(ClientAppMapper.toClient(citySenderDB, cityAddresseeDB));

        //armo en el mapper toda la informacion del envio y lo pongo en la clase shipment
        Shipment shipment = Shipment.create(info.distance(),sender ,addressee,packageModel);
        //mando a guardar el envio en el repository
        Shipment savedShipment = IShipmentRepository.save(shipment);
        return ShipmentAppMapper.toDto(savedShipment);
    }
}
