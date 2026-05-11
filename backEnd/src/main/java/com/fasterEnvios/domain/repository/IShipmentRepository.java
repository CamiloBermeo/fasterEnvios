package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.Shipment;

import java.util.Optional;

public interface IShipmentRepository {
    Shipment save(Shipment shipment);
    Optional<Shipment> findByTrackingNumber(String tracingNumber);

}
