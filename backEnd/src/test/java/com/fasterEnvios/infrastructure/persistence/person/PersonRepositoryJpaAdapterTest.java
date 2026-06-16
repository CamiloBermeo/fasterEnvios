package com.fasterEnvios.infrastructure.persistence.person;

import com.fasterEnvios.domain.exceptions.city.CityNotFoundException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.entity.PersonEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(PersonRepositoryJpaAdapter.class)
class PersonRepositoryJpaAdapterTest {

    @Autowired
    private PersonRepositoryJpaAdapter adapter;
    @Autowired
    private TestEntityManager entityManager;

    @Nested
    class SaveTest{
        @Test
        void save_shouldPersistInDatabaseAndReturnCorrectModel() {

            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = entityManager.persistAndFlush(cityEntity);

            Person person = buildPersonForTest(cityEntity);
            Person savePerson = adapter.save(person);

            assertNotNull(savePerson);

            PersonEntity entityInDb = entityManager.getEntityManager()
                    .createQuery("SELECT p FROM PersonEntity p WHERE p.identityDocument = :identity", PersonEntity.class)
                    .setParameter("identity", "IDENTITY_DOCUMENT")
                    .getSingleResult();
            assertNotNull(entityInDb);
            assertEquals(savePerson.getIdentityDocument(), entityInDb.getIdentityDocument());
            assertEquals(savePerson.getName(), entityInDb.getName());
            assertNotNull(entityInDb.getCity());

        }
        @Test
        void save_shouldPersistInDatabaseAndThrowCityNotFoundException() {

            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity.setId(1233L);
            Person person = buildPersonForTest(cityEntity);

            assertThrows(CityNotFoundException.class, ()-> adapter.save(person));
        }
    }

    @Nested
    class FindByIdTest{
        @Test
        void findPersonByIdentityDocument() {

            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = entityManager.persistAndFlush(cityEntity);

             Person savePerson = adapter.save(buildPersonForTest(cityEntity));

            Optional<Person> person = adapter.findPersonByIdentityDocument("IDENTITY_DOCUMENT");
            assertTrue(person.isPresent());
            assertEquals(savePerson.getIdentityDocument(), person.get().getIdentityDocument());
        }
    }
    private Person buildPersonForTest(CityDescriptionEntity cityEntity) {
        CityDescription city = CityDescription.builder()
                .withId(cityEntity.getId())
                .build();
       return Person.builder()
                .withName("NAME")
                .withLastName("LAST_NAME")
                .withIdentityDocument("IDENTITY_DOCUMENT")
                .withPhoneNumber("PHONE_NUMBER")
                .withAddress("ADDRESS")
                .withCity(city)
                .build();
    }
    private CityDescriptionEntity buildCityEntityForTest() {
        CityDescriptionEntity cityEntity = new CityDescriptionEntity();
        cityEntity.setName("Cali");
        cityEntity.setCountry("COLOMBIA");
        return cityEntity;
    }
}