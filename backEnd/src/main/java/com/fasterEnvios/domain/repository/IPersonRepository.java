package com.fasterEnvios.domain.repository;

import com.fasterEnvios.domain.model.Person;

import java.util.Optional;

public interface IPersonRepository {
    Person save(Person person);
    Optional<Person> findPersonByIdentityDocument(String identityDocument);
}
