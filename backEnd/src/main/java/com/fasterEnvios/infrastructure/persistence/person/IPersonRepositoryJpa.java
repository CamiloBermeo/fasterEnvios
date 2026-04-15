package com.fasterEnvios.infrastructure.persistence.person;

import com.fasterEnvios.infrastructure.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepositoryJpa extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findPersonByIdentityDocument(String  identityDocument);
}
