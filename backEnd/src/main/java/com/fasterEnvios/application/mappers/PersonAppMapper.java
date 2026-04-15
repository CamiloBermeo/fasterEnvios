package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.person.PersonRequestDTO;
import com.fasterEnvios.domain.model.CityDescription;
import com.fasterEnvios.domain.model.Person;

public class PersonAppMapper {

    public static Person toModel (PersonRequestDTO dto, CityDescription cityDescription) {
        return Person.builder()

                .withName(dto.name())
                .withLastName(dto.lastName())
                .withPhoneNumber(dto.phoneNumber())
                .withAddress(dto.address())
                .withIdentityDocument(dto.identityDocument())
                .withCity(cityDescription)
                .build();
    }

}
