package com.fasterEnvios.application.useCase.Shipment;

import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.repository.IShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindShipmentByTrackingNumber {
    private IShipmentRepository shipmentRepository;

    public Optional<Shipment> execute(String tracingNumber){
        return shipmentRepository.findByTrackingNumber(tracingNumber);
    }

}
