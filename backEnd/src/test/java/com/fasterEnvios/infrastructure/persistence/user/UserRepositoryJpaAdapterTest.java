package com.fasterEnvios.infrastructure.persistence.user;

import com.fasterEnvios.domain.exceptions.city.CityNotFoundException;
import com.fasterEnvios.domain.exceptions.role.RoleNotFoundDataBaseException;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Role;
import com.fasterEnvios.domain.model.UserModel;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.entity.RoleEntity;
import com.fasterEnvios.infrastructure.entity.UserEntity;
import com.fasterEnvios.infrastructure.persistence.city.CityRepositoryJpaAdapter;
import com.fasterEnvios.infrastructure.persistence.city.ICityRepositoryJpa;
import com.fasterEnvios.infrastructure.persistence.role.IRoleRepositoryJpa;
import com.fasterEnvios.infrastructure.persistence.role.RoleRepositoryJpaAdapter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({UserRepositoryJpaAdapter.class,
        CityRepositoryJpaAdapter.class,
        RoleRepositoryJpaAdapter.class
})
@ActiveProfiles("test")
class UserRepositoryJpaAdapterTest {

    @Autowired
    UserRepositoryJpaAdapter adapter;
    @Autowired
    TestEntityManager testEntityManager;

    @Nested
    class FindByEmailTest {
        @Test
        void findByEmail() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            RoleEntity roleEntity = buildRoleEntityForTest();
            roleEntity = testEntityManager.persistAndFlush(roleEntity);

            adapter.save(buildUserForTest(cityEntity, roleEntity));

            Optional<UserModel> saveUser = adapter.findByEmail("EMAIL");

            assertTrue(saveUser.isPresent());
            assertEquals("EMAIL", saveUser.get().getEmail());

        }
    }

    @Nested
    class SaveTest {
        @Test
        void save_shouldPersistInDatabaseAndReturnCorrectMode() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            RoleEntity roleEntity = buildRoleEntityForTest();
            roleEntity = testEntityManager.persistAndFlush(roleEntity);

            UserModel user = buildUserForTest(cityEntity, roleEntity);
            UserModel saveUser = adapter.save(user);
            assertNotNull(saveUser);

            UserEntity userInDb = testEntityManager.getEntityManager()
                    .createQuery("SELECT u FROM UserEntity u WHERE u.identityDocument = :identity", UserEntity.class)
                    .setParameter("identity", "1234567")
                    .getSingleResult();

            assertNotNull(userInDb);
            assertEquals(saveUser.getIdentityDocument(), userInDb.getIdentityDocument());
            assertEquals(saveUser.getEmail(), userInDb.getEmail());
            assertNotNull(userInDb.getCity());
            assertNotNull(userInDb.getRole());
        }

        @Test
        void save_shouldPersistInDatabaseAndTrowCityNotFoundException() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity.setId(123L);
            RoleEntity roleEntity = buildRoleEntityForTest();
            roleEntity = testEntityManager.persistAndFlush(roleEntity);

            UserModel user = buildUserForTest(cityEntity, roleEntity);
            assertThrows(CityNotFoundException.class, () -> adapter.save(user));

        }

        @Test
        void save_shouldPersistInDatabaseAndTrowRoleNotFoundDataBaseException() {
            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            RoleEntity roleEntity = buildRoleEntityForTest();
            roleEntity.setId(1234L);

            UserModel user = buildUserForTest(cityEntity, roleEntity);
            assertThrows(RoleNotFoundDataBaseException.class, () -> adapter.save(user));
        }
    }

    @Nested
    class FindByDocumentNumberTest {
        @Test
        void findByDocumentNumber() {

            CityDescriptionEntity cityEntity = buildCityEntityForTest();
            cityEntity = testEntityManager.persistAndFlush(cityEntity);
            RoleEntity roleEntity = buildRoleEntityForTest();
            roleEntity = testEntityManager.persistAndFlush(roleEntity);

            adapter.save(buildUserForTest(cityEntity, roleEntity));

            Optional<UserModel> saveUser = adapter.findByDocument("1234567");

            assertTrue(saveUser.isPresent());
            assertEquals("1234567", saveUser.get().getIdentityDocument());
        }
    }

    private UserModel buildUserForTest(CityDescriptionEntity cityEntity, RoleEntity roleEntity) {
        CityDescription city = CityDescription.builder()
                .withId(cityEntity.getId())
                .build();
        Role role = Role.builder()
                .withId(roleEntity.getId())
                .build();
        return UserModel.builder()
                .withName("USER_NAME")
                .withLastName("USER_LASTNAME")
                .withEmail("EMAIL")
                .withCity(city)
                .withPasswordHash("PASSWORD")
                .withPhoneNumber("1234")
                .withIdentityDocument("1234567")
                .withRole(role)
                .build();
    }

    private CityDescriptionEntity buildCityEntityForTest() {
        CityDescriptionEntity cityEntity = new CityDescriptionEntity();
        cityEntity.setName("Cali");
        cityEntity.setCountry("COLOMBIA");
        return cityEntity;
    }

    private RoleEntity buildRoleEntityForTest() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("ROLE_ADMIN");
        roleEntity.setDescription("DESCRIPCION_TEST");
        return roleEntity;
    }
}