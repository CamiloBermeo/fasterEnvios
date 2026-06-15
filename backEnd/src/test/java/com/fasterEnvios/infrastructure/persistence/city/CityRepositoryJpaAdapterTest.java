package com.fasterEnvios.infrastructure.persistence.city;

import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.entity.ShipmentEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(CityRepositoryJpaAdapter.class)
class CityRepositoryJpaAdapterTest {

    @Autowired
    private CityRepositoryJpaAdapter adapter;
    @Autowired
    private TestEntityManager testEntityManager;

    @Nested
    class FindCityByNameTest {
        @Test
        void findCityByName() {
            CityDescription city = buildCityModelForTest();
            CityDescription cityInDb = adapter.save(city);

            Optional<CityDescription> result = adapter.findCityByName(cityInDb.getName());
            assertTrue(result.isPresent());
            assertEquals(cityInDb.getName(), result.get().getName());
        }
    }

    @Nested
    class SaveTest {
        @Test
        void save_shouldPersistCityAndReturnModel() {

            CityDescription city = buildCityModelForTest();
            CityDescription cityInDb = adapter.save(city);
            assertNotNull(cityInDb);
            assertNotNull(cityInDb);
            assertNotNull(cityInDb.getId()); // BD generó el ID
            assertEquals("CITY_TEST", cityInDb.getName());

            CityDescriptionEntity cityEntityInDb = testEntityManager.getEntityManager()
                    .createQuery("SELECT c FROM CityDescriptionEntity c WHERE c.name = :name", CityDescriptionEntity.class)
                    .setParameter("name", "CITY_TEST")
                    .getSingleResult();

            assertNotNull(cityEntityInDb);
            assertEquals("CITY_TEST", cityEntityInDb.getName());
        }
    }
    private CityDescription buildCityModelForTest() {
        return CityDescription.builder()
                .withName("CITY_TEST")
                .withCountry("COLOMBIA")
                .withLongitude(123.4)
                .withLatitude(123.5)
                .build();
    }
}