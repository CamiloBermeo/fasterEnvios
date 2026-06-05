package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.shipment.NewShipmentRequestDTO;
import com.fasterEnvios.application.dto.shipment.NewShipmentResponseDTO;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.domain.model.PackageModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ShipmentAppMapper {


    public static NewShipmentResponseDTO toDto(Shipment shipment){
        return new NewShipmentResponseDTO(
                shipment.getTrackingNumber(),
                shipment.getState().toString(),
                shipment.getSender().getCity().getName(),
                shipment.getAddressee().getCity().getName(),
                String.valueOf(shipment.getDistance()+"Km"),
                String.valueOf("$"+shipment.getTotalAmount()+" COP"),
                shipment.getEstimatedDeliveryDate(),
                shipment.getCreatedAt()
        );
    }

}
