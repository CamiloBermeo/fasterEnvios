package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.Shipment;

public interface IShipmentRepository {
    Shipment save(Shipment shipment);

}
