package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.Person;
import com.fasterEnvios.infrastructure.entity.PersonEntity;

public class PersonInfraMapper {

    public static PersonEntity toEntity(Person person) {
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .lastName(person.getLastName())
                .identityDocument(person.getIdentityDocument())
                .phoneNumber(person.getPhoneNumber())
                .city(CityInfraMapper.toEntity(person.getCity()))
                .address(person.getAddress())
                .build();
    }

    public static Person toModel (PersonEntity entity) {
        Person.PersonBuilder personBuilder = Person.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withLastName(entity.getLastName())
                .withIdentityDocument(entity.getIdentityDocument())
                .withPhoneNumber(entity.getPhoneNumber())
                .withAddress(entity.getAddress())
                .withCity(CityInfraMapper.toDomain(entity.getCity()));
        return personBuilder.build();
    }
}
