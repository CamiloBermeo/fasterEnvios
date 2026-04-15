package com.fasterEnvios.infrastructure.persistence.person;

import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.domain.repository.IPersonRepository;
import com.fasterEnvios.infrastructure.entity.PersonEntity;
import com.fasterEnvios.infrastructure.mapper.PersonInfraMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryJpaAdapter implements IPersonRepository {
    private final IPersonRepositoryJpa jpa;
    @Override
    public Person save(Person person) {
        return PersonInfraMapper.toModel(jpa.save(PersonInfraMapper.toEntity(person)));
    }

    @Override
    public Optional<Person> findPersonByIdentityDocument(String identityDocument) {
        Optional<PersonEntity> personEntity = jpa.findPersonByIdentityDocument(identityDocument);
        return personEntity.map(PersonInfraMapper::toModel);
    }
}
