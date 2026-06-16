package com.fasterEnvios.infrastructure.persistence.payment;

import com.fasterEnvios.domain.exceptions.payment.PaymentMethodNotFoundException;
import com.fasterEnvios.domain.exceptions.person.PersonNotFoundException;
import com.fasterEnvios.domain.exceptions.shipment.ShipmentNotFoundException;
import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.infrastructure.entity.*;
import com.fasterEnvios.infrastructure.persistence.shipment.ShipmentRepositoryJpaAdapter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(PaymentRepositoryJpaAdapter.class)
class PaymentRepositoryJpaAdapterTest {
    @Autowired
    private PaymentRepositoryJpaAdapter adapter;
    @Autowired
    private TestEntityManager testEntityManager;



    @Nested
    class SavePaymentTest {
        @Test
        void save_shouldPersistInDatabaseAndReturnCorrectModel() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            PersonEntity senderEntity = buildPersonEntityForTest(cityEntity);
            senderEntity = testEntityManager.persistAndFlush(senderEntity);
            PersonEntity addresseeEntity = buildPersonEntityForTest(cityEntity);
            addresseeEntity = testEntityManager.persistAndFlush(addresseeEntity);
            PackageEntity packageEntity = buildPackageForTest();
            packageEntity = testEntityManager.persistAndFlush(packageEntity);
            ShipmentEntity shipment = buildShipmentEntityForTest(senderEntity, addresseeEntity, packageEntity);
            shipment = testEntityManager.persistAndFlush(shipment);
            PaymentMethodEntity paymentMethod = buildPaymentMethodEntityForTest();
            paymentMethod = testEntityManager.persistAndFlush(paymentMethod);
            PersonEntity payingPerson = buildPersonEntityForTest(cityEntity);
            payingPerson = testEntityManager.persistAndFlush(payingPerson);

            PaymentTransaction payment = buildPaymentTransactionForTest(shipment,payingPerson,paymentMethod);
            PaymentTransaction savePayment = adapter.save(payment);

            assertNotNull(savePayment);

            PaymentTransactionEntity paymentInDb = testEntityManager.getEntityManager()
                    .createQuery("SELECT p FROM PaymentTransactionEntity p WHERE p.idTransaction = :idTrans", PaymentTransactionEntity.class)
                    .setParameter("idTrans", "1234QWER")
                    .getSingleResult();
            assertNotNull(paymentInDb);
            assertEquals("1234QWER", paymentInDb.getIdTransaction());
        }
        @Test
        void save_shouldPersistInDatabaseAndTrowShipmentNotFoundException() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            PersonEntity senderEntity = buildPersonEntityForTest(cityEntity);
            senderEntity = testEntityManager.persistAndFlush(senderEntity);
            PersonEntity addresseeEntity = buildPersonEntityForTest(cityEntity);
            addresseeEntity = testEntityManager.persistAndFlush(addresseeEntity);
            PackageEntity packageEntity = buildPackageForTest();
            packageEntity = testEntityManager.persistAndFlush(packageEntity);
            ShipmentEntity shipment = buildShipmentEntityForTest(senderEntity, addresseeEntity, packageEntity);
            shipment.setId(333L);
            PaymentMethodEntity paymentMethod = buildPaymentMethodEntityForTest();
            paymentMethod = testEntityManager.persistAndFlush(paymentMethod);
            PersonEntity payingPerson = buildPersonEntityForTest(cityEntity);
            payingPerson = testEntityManager.persistAndFlush(payingPerson);

            PaymentTransaction payment = buildPaymentTransactionForTest(shipment,payingPerson,paymentMethod);

            assertThrows(ShipmentNotFoundException.class, ()-> adapter.save(payment));

        }
        @Test
        void save_shouldPersistInDatabaseAndTrowPaymentMethodNotFoundException() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            PersonEntity senderEntity = buildPersonEntityForTest(cityEntity);
            senderEntity = testEntityManager.persistAndFlush(senderEntity);
            PersonEntity addresseeEntity = buildPersonEntityForTest(cityEntity);
            addresseeEntity = testEntityManager.persistAndFlush(addresseeEntity);
            PackageEntity packageEntity = buildPackageForTest();
            packageEntity = testEntityManager.persistAndFlush(packageEntity);
            ShipmentEntity shipment = buildShipmentEntityForTest(senderEntity, addresseeEntity, packageEntity);
            shipment = testEntityManager.persistAndFlush(shipment);
            PaymentMethodEntity paymentMethod = buildPaymentMethodEntityForTest();
            paymentMethod.setId(434L);
            PersonEntity payingPerson = buildPersonEntityForTest(cityEntity);
            payingPerson = testEntityManager.persistAndFlush(payingPerson);

            PaymentTransaction payment = buildPaymentTransactionForTest(shipment,payingPerson,paymentMethod);

            assertThrows(PaymentMethodNotFoundException.class, ()-> adapter.save(payment));

        }
        @Test
        void save_shouldPersistInDatabaseAndTrowPersonNotFoundException() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            PersonEntity senderEntity = buildPersonEntityForTest(cityEntity);
            senderEntity = testEntityManager.persistAndFlush(senderEntity);
            PersonEntity addresseeEntity = buildPersonEntityForTest(cityEntity);
            addresseeEntity = testEntityManager.persistAndFlush(addresseeEntity);
            PackageEntity packageEntity = buildPackageForTest();
            packageEntity = testEntityManager.persistAndFlush(packageEntity);
            ShipmentEntity shipment = buildShipmentEntityForTest(senderEntity, addresseeEntity, packageEntity);
            shipment = testEntityManager.persistAndFlush(shipment);
            PaymentMethodEntity paymentMethod = buildPaymentMethodEntityForTest();
            paymentMethod = testEntityManager.persistAndFlush(paymentMethod);
            PersonEntity payingPerson = buildPersonEntityForTest(cityEntity);
            payingPerson.setId(444L);

            PaymentTransaction payment = buildPaymentTransactionForTest(shipment,payingPerson,paymentMethod);

            assertThrows(PersonNotFoundException.class, ()-> adapter.save(payment));

        }
    }

    private PaymentTransaction buildPaymentTransactionForTest(ShipmentEntity shipmentEntity, PersonEntity payingPersonEntity,
                                                              PaymentMethodEntity paymentMethodEntity) {
        Shipment shipment = Shipment.builder()
                .withId(shipmentEntity.getId())
                .build();
        CityDescription city = CityDescription.builder()
                .withId(5L)
                .withName("Cali")
                .withCountry("COLOMBIA")
                .withLatitude(123.4)
                .withLongitude(432.1)
                .build();
        Person person = Person.builder()
                .withId(payingPersonEntity.getId())
                .withCity(city)
                .build();
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .withId(paymentMethodEntity.getId())
                .build();
        return PaymentTransaction.builder()
                .withId(3L)
                .withIdTransaction("1234QWER")
                .withShipments(shipment)
                .withPayingPerson(person)
                .withPaymentMethods(paymentMethod)
                .withAmount(BigDecimal.valueOf(50000))
                .withPaymentDate(LocalDateTime.now())
                .withPaymentStatus(PaymentStatusEnum.PAYMENT)
                .withObservation("OBSERVACIONES")
                .build();
    }
    private PaymentMethodEntity buildPaymentMethodEntityForTest(){
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setName("EFECTIVO");
        paymentMethodEntity.setStatus(true);
        return paymentMethodEntity;
    }
    private CityDescriptionEntity buildCityEntityForTest() {
        CityDescriptionEntity cityEntity = new CityDescriptionEntity();
        cityEntity.setName("Cali");
        cityEntity.setCountry("COLOMBIA");
        return cityEntity;
    }

    private PersonEntity buildPersonEntityForTest(CityDescriptionEntity cityEntity) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("Camilo");
        personEntity.setIdentityDocument("1004036028");
        personEntity.setAddress("DIRECCION_TEST");
        personEntity.setPhoneNumber("1234567");
        personEntity.setLastName("APELLIDO_TEST");
        personEntity.setCity(cityEntity);
        return personEntity;
    }

    private PackageEntity buildPackageForTest() {
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setDescription("MOTO");
        packageEntity.setDimensions(1.0); // Asumiendo double/float
        packageEntity.setDeclaredValue(BigDecimal.valueOf(111));
        return packageEntity;
    }

    private ShipmentEntity buildShipmentEntityForTest(PersonEntity senderEntity, PersonEntity addresseeEntity, PackageEntity packageEntity) {

        ShipmentEntity entity = new ShipmentEntity();
                entity.setTrackingNumber("TRACKING-REAL");
                entity.setSender(senderEntity);
                entity.setAddressee(addresseeEntity);
                entity.setPackages(packageEntity);
                entity.setCreatedAt(LocalDateTime.now());
                entity.setEstimatedDeliveryDate(LocalDateTime.now());
                entity.setTotalAmount(BigDecimal.valueOf(500000));
                entity.setState(StateEnum.RECEIVED.toString());
        return entity;
    }
}