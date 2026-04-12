package com.fasterEnvios.infrastructure.persistence.shipment;

import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShipmentRepositoryJpa extends JpaRepository<ShipmentEntity, Long> {

}
