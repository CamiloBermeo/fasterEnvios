package com.fasterEnvios.infrastructure.persistence.shipment;

import com.fasterEnvios.domain.model.Shipment;
import com.fasterEnvios.domain.repository.IShipmentRepository;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import com.fasterEnvios.infrastructure.mapper.PackageInfraMapper;
import com.fasterEnvios.infrastructure.mapper.PaymentMethodInfraMapper;
import com.fasterEnvios.infrastructure.mapper.PaymentTransactionInfraMapper;
import com.fasterEnvios.infrastructure.mapper.ShipmentInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShipmentRepositoryJpaAdapter implements IShipmentRepository {
    private final IShipmentRepositoryJpa jpa;
    @Override
    public Shipment save (Shipment shipmentModel){
        ShipmentEntity shipmentEntity = ShipmentInfraMapper.toEntity(shipmentModel,
                PackageInfraMapper.toEntity(shipmentModel.getPackageModels()),
                PaymentTransactionInfraMapper.toEntity(shipmentModel.getPaymentTransaction(),
                        PaymentMethodInfraMapper.toEntity(shipmentModel.getPaymentTransaction().getPaymentMethods())));

        return ShipmentInfraMapper.toModel(jpa.save(shipmentEntity));
    }
}
