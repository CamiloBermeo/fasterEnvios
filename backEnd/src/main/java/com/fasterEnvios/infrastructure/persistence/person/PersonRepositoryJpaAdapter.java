package com.fasterEnvios.infrastructure.persistence.person;

import com.fasterEnvios.domain.exceptions.city.CityNotFoundException;
import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.domain.repository.IPersonRepository;
import com.fasterEnvios.infrastructure.entity.CityDescriptionEntity;
import com.fasterEnvios.infrastructure.entity.PersonEntity;
import com.fasterEnvios.infrastructure.mapper.PersonInfraMapper;
import com.fasterEnvios.infrastructure.persistence.city.ICityRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryJpaAdapter implements IPersonRepository {
    private final IPersonRepositoryJpa jpa;
    private final ICityRepositoryJpa cityJpa;
    @Override
    public Person save(Person person) {
        CityDescriptionEntity city = cityJpa.findById(person.getCity().getId())
                .orElseThrow(()-> new CityNotFoundException(person.getCity().getName()));
        PersonEntity entity = PersonInfraMapper.toEntity(person);
        entity.setCity(city);
        return PersonInfraMapper.toModel(jpa.save(entity));
    }

    @Override
    public Optional<Person> findPersonByIdentityDocument(String identityDocument) {
        Optional<PersonEntity> personEntity = jpa.findPersonByIdentityDocument(identityDocument);
        return personEntity.map(PersonInfraMapper::toModel);
    }
}
