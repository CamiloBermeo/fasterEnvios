package com.fasterEnvios.infrastructure.persistence.payment;

import com.fasterEnvios.domain.exceptions.payment.PaymentMethodNotFoundException;
import com.fasterEnvios.domain.exceptions.person.PersonNotFoundException;
import com.fasterEnvios.domain.exceptions.shipment.ShipmentNotFoundException;
import com.fasterEnvios.domain.model.PaymentMethod;
import com.fasterEnvios.domain.model.PaymentTransaction;
import com.fasterEnvios.domain.repository.IPaymentRepository;
import com.fasterEnvios.infrastructure.entity.PaymentMethodEntity;
import com.fasterEnvios.infrastructure.entity.PaymentTransactionEntity;
import com.fasterEnvios.infrastructure.entity.PersonEntity;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import com.fasterEnvios.infrastructure.mapper.PaymentMethodInfraMapper;
import com.fasterEnvios.infrastructure.mapper.PaymentTransactionInfraMapper;
import com.fasterEnvios.infrastructure.persistence.person.IPersonRepositoryJpa;
import com.fasterEnvios.infrastructure.persistence.shipment.IShipmentRepositoryJpa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryJpaAdapter implements IPaymentRepository {
    private final IPaymentRepositoryJpa jpa;
    private final IShipmentRepositoryJpa shipmentJpa;
    private final IPaymentMethodRepositoryJpa jpaMethod;
    private final IPersonRepositoryJpa jpaPerson;

    @Override
    public Optional<PaymentMethod> findPaymentMethodByName(String paymentMethodName) {
        Optional<PaymentMethodEntity> entity = jpaMethod.findByName(paymentMethodName);
        return entity.map(PaymentMethodInfraMapper::toModel);
    }

    @Override
    @Transactional
    public PaymentTransaction save(PaymentTransaction paymentTransaction) {
        PaymentTransactionEntity entity = buildManagedEntity(paymentTransaction);
        return PaymentTransactionInfraMapper.toModel(jpa.save(entity));
    }

    private PaymentTransactionEntity buildManagedEntity(PaymentTransaction paymentTransaction) {
        ShipmentEntity shipmentEntity = shipmentJpa
                .findById(paymentTransaction.getShipments().getId())
                .orElseThrow(() -> new ShipmentNotFoundException(paymentTransaction.getShipments().getId().toString()));

        PaymentMethodEntity paymentMethod = jpaMethod
                .findById(paymentTransaction.getPaymentMethods().getId())
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentTransaction.getPaymentMethods().getId().toString()));
        PersonEntity payingPerson = jpaPerson
                .findById(paymentTransaction.getPayingPerson().getId())
                .orElseThrow(() -> new PersonNotFoundException(paymentTransaction.getPayingPerson().getId().toString()));
        PaymentTransactionEntity entity = PaymentTransactionInfraMapper.toEntity(paymentTransaction);
        entity.setShipment(shipmentEntity);
        entity.setPaymentMethod(paymentMethod);
        entity.setPayingPerson(payingPerson);
        return entity;
    }
}