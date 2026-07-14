package com.fasterEnvios.infrastructure.persistence.shipment;

import com.fasterEnvios.domain.model.*;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.entity.PackageEntity;
import com.fasterEnvios.infrastructure.entity.PersonEntity;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import com.fasterEnvios.infrastructure.persistence.city.CityRepositoryJpaAdapter;
import com.fasterEnvios.infrastructure.persistence.city.ICityRepositoryJpa;
import com.fasterEnvios.infrastructure.persistence.person.IPersonRepositoryJpa;
import com.fasterEnvios.infrastructure.persistence.person.PersonRepositoryJpaAdapter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({ShipmentRepositoryJpaAdapter.class,
        PersonRepositoryJpaAdapter.class,
        CityRepositoryJpaAdapter.class
})
@ActiveProfiles("test")
class ShipmentRepositoryJpaAdapterTest {
    @Autowired
    private ShipmentRepositoryJpaAdapter adapter;
    @Autowired
    private TestEntityManager testEntityManager;

    @Nested
    class SaveIntegrationTests {
        @Test
        void save_shouldPersistInDatabaseAndReturnCorrectModel() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            // No le pones ID manualmente, dejas que la BD lo genere (GenerationType.IDENTITY)
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            PersonEntity senderEntity = buildPersonEntityForTest(cityEntity);
            senderEntity = testEntityManager.persistAndFlush(senderEntity);
            PersonEntity addresseeEntity = buildPersonEntityForTest(cityEntity);
            addresseeEntity = testEntityManager.persistAndFlush(addresseeEntity);
            PackageEntity packageEntity = buildPackageForTest();
            packageEntity = testEntityManager.persistAndFlush(packageEntity);
            Shipment shipment = buildShipmentForTest(senderEntity,
                    addresseeEntity,
                    packageEntity);

            Shipment saveModel = adapter.save(shipment);

            assertNotNull(saveModel);

            ShipmentEntity entityInDb = testEntityManager.getEntityManager()
                    .createQuery("SELECT s FROM ShipmentEntity s WHERE s.trackingNumber = :tracking", ShipmentEntity.class)
                    .setParameter("tracking", "TRACKING-REAL")
                    .getSingleResult();

            assertNotNull(entityInDb);
            assertEquals("TRACKING-REAL", entityInDb.getTrackingNumber());
            assertEquals("RECEIVED", entityInDb.getState());
            assertEquals(0, BigDecimal.valueOf(500000).compareTo(entityInDb.getTotalAmount()));
            assertNotNull(entityInDb.getSender());
            assertNotNull(entityInDb.getPackages());
        }
    }

    @Nested
    class FindByTrackingNumberTests {
        @Test
        void findByTrackingNumber() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            PersonEntity senderEntity = buildPersonEntityForTest(cityEntity);
            senderEntity = testEntityManager.persistAndFlush(senderEntity);
            PersonEntity addresseeEntity = buildPersonEntityForTest(cityEntity);
            addresseeEntity = testEntityManager.persistAndFlush(addresseeEntity);
            PackageEntity packageEntity = buildPackageForTest();
            packageEntity = testEntityManager.persistAndFlush(packageEntity);
            adapter.save(buildShipmentForTest(senderEntity, addresseeEntity, packageEntity));

            Optional<Shipment> result = adapter.findByTrackingNumber("TRACKING-REAL");

            assertTrue(result.isPresent());
            assertEquals("TRACKING-REAL", result.get().getTrackingNumber());
        }
    }
    private CityDescriptionEntity buildCityEntityForTest(){
        CityDescriptionEntity cityEntity = new CityDescriptionEntity();
        cityEntity.setName("Cali");
        cityEntity.setCountry("COLOMBIA");
        return cityEntity;
    }
    private PersonEntity buildPersonEntityForTest(CityDescriptionEntity cityEntity ){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName("Camilo");
        personEntity.setIdentityDocument("1004036028");
        personEntity.setAddress("DIRECCION_TEST");
        personEntity.setPhoneNumber("1234567");
        personEntity.setLastName("APELLIDO_TEST");
        personEntity.setCity(cityEntity);
        return personEntity;
    }
    private PackageEntity buildPackageForTest(){
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setDescription("MOTO");
        packageEntity.setDimensions(1.0); // Asumiendo double/float
        packageEntity.setDeclaredValue(BigDecimal.valueOf(111));
        return packageEntity;
    }
    private Shipment buildShipmentForTest( PersonEntity senderEntity, PersonEntity addresseeEntity, PackageEntity packageEntity) {
        CityDescription city = CityDescription.builder()
                .withId(3L)
                .withName("Cali")
                .withCountry("COLOMBIA")
                .withLatitude(123.4)
                .withLongitude(432.1)
                .build();
        PackageModel packageModel = PackageModel.builder()
                .withId(packageEntity.getId())
                .build();
        Person sender = Person.builder()
                .withId(senderEntity.getId())
                .withCity(city)
                .build();
        Person addressee = Person.builder()
                .withId(addresseeEntity.getId())
                .withCity(city)
                .build();

        return Shipment.builder()
                .withId(3L)
                .withTrackingNumber("TRACKING-REAL")
                .withSender(sender)
                .withAddressee(addressee)
                .withPackages(packageModel)
                .withCreatedAt(LocalDateTime.now())
                .withEstimatedDeliveryDate(LocalDateTime.now())
                .withTotalAmount(BigDecimal.valueOf(500000))
                .withState(StateEnum.RECEIVED)
                .build();
    }

}